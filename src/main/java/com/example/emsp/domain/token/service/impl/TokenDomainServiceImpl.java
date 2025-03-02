package com.example.emsp.domain.token.service.impl;

import com.example.emsp.api.model.form.CreateTokenForm;
import com.example.emsp.api.model.form.UpdateTokenForm;
import com.example.emsp.api.model.resp.PageResult;
import com.example.emsp.common.enums.Errors;
import com.example.emsp.common.enums.Status;
import com.example.emsp.common.enums.TokenType;
import com.example.emsp.domain.account.service.Impl.AccountDomainService;
import com.example.emsp.domain.token.entity.AccountTokenRel;
import com.example.emsp.domain.token.entity.Token;
import com.example.emsp.domain.token.repository.AccountTokenRelRepository;
import com.example.emsp.domain.token.repository.TokenRepository;
import com.example.emsp.domain.token.service.TokenDomainService;
import com.example.emsp.domain.token.strategy.TokenTypeVerifyStrategy;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class TokenDomainServiceImpl implements TokenDomainService {

    private final AccountDomainService accountDomainService;
    private final TokenRepository tokenRepository;
    private final AccountTokenRelRepository accountTokenRelRepository;
    private final List<TokenTypeVerifyStrategy> tokenTypeVerifyStrategies;
    private final Map<TokenType, TokenTypeVerifyStrategy> strategyMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        tokenTypeVerifyStrategies.forEach(sub -> this.strategyMap.put(sub.supportTokenType(), sub));
    }

    public Token createToken(CreateTokenForm form) {
        Errors.ILLEGAL_ARG.exceptionWhenFalse(strategyMap.get(form.getType()).verify(form), "Token.Value.Not.Match.Type");
        Token token = form.toToken();
        token.setStatus(Status.CREATED);
        Instant now = Instant.now();
        token.setCreatedAt(now);
        token.setLastUpdated(now);
        tokenRepository.insertToken(token);
        return token;
    }

    public Token updateStatusOfToken(Integer tokenId, UpdateTokenForm form) {
        Token token = tokenRepository.getTokenById(tokenId);
        Status updatedStatus = form.getStatus();

        Errors.ILLEGAL_ARG.exceptionWhenFalse(!Objects.equals(Status.CREATED, updatedStatus),
                "Updated.Status.Can.Not.Be.Created");

        if (!Objects.equals(token.getStatus(), updatedStatus)) {
            token.setStatus(updatedStatus);
            tokenRepository.updateStatusOfToken(tokenId, updatedStatus);

        }
        return token;
    }

    public PageResult<Token> searchTokens(Pageable pageable) {
        int pageNum = pageable.getPageNumber() + 1;
        int pageSize = pageable.getPageSize();
        String orderBy = pageable.getSort().toString().replace(":", " ");

        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Token> pos = tokenRepository.pageTokens();
        PageInfo<Token> pageInfo = new PageInfo<>(pos);

        PageResult.Page page = new PageResult.Page(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal());
        return new PageResult<>(page, pos);
    }

    public void assignToken(Integer tokenId, Integer accountId) {
        // Check if account id and token id exist
        accountDomainService.getAccountById(accountId);
        tokenRepository.getTokenById(tokenId);

        AccountTokenRel accountTokenRel = new AccountTokenRel();
        accountTokenRel.setAccountId(accountId);
        accountTokenRel.setTokenId(tokenId);
        accountTokenRelRepository.insertAccountTokenRel(accountTokenRel);
    }

}
