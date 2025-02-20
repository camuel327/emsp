package com.example.emsp.service;

import com.example.emsp.mapper.AccountTokenRelMapper;
import com.example.emsp.mapper.TokenMapper;
import com.example.emsp.model.enums.Errors;
import com.example.emsp.model.enums.Status;
import com.example.emsp.model.enums.TokenType;
import com.example.emsp.model.form.CreateTokenForm;
import com.example.emsp.model.form.UpdateTokenForm;
import com.example.emsp.model.po.AccountTokenRelPO;
import com.example.emsp.model.po.TokenPO;
import com.example.emsp.model.resp.PageResult;
import com.example.emsp.model.vo.TokenVO;
import com.example.emsp.strategy.TokenTypeVerifyStrategy;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final AccountService accountService;
    private final TokenMapper tokenMapper;
    private final AccountTokenRelMapper accountTokenRelMapper;
    private final List<TokenTypeVerifyStrategy> tokenTypeVerifyStrategies;
    private final Map<TokenType, TokenTypeVerifyStrategy> strategyMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        tokenTypeVerifyStrategies.forEach(sub -> this.strategyMap.put(sub.supportTokenType(), sub));
    }

    public TokenVO createToken(CreateTokenForm form) {
        Errors.ILLEGAL_ARG.exceptionWhenFalse(strategyMap.get(form.getType()).verify(form), "Token.Value.Not.Match.Type");
        TokenPO tokenPO = form.toTokenPO();
        tokenPO.setStatus(Status.CREATED);
        Instant now = Instant.now();
        tokenPO.setCreatedAt(now);
        tokenPO.setLastUpdated(now);
        tokenMapper.insertToken(tokenPO);
        return TokenVO.of(tokenPO);
    }

    public TokenVO updateStatusOfToken(Integer tokenId, UpdateTokenForm form) {
        TokenPO tokenPO = getTokenById(tokenId);
        Status updatedStatus = form.getStatus();

        Errors.ILLEGAL_ARG.exceptionWhenFalse(!Objects.equals(Status.CREATED, updatedStatus),
                "Updated.Status.Can.Not.Be.Created");

        if (!Objects.equals(tokenPO.getStatus(), updatedStatus)) {
            tokenPO.setStatus(updatedStatus);
            tokenMapper.updateStatusOfToken(tokenId, updatedStatus);

        }
        return TokenVO.of(tokenPO);
    }

    public PageResult<TokenVO> searchTokens(Pageable pageable) {
        int pageNum = pageable.getPageNumber() + 1;
        int pageSize = pageable.getPageSize();
        String orderBy = pageable.getSort().toString().replace(":", " ");

        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<TokenPO> pos = tokenMapper.pageTokens();
        PageInfo<TokenPO> pageInfo = new PageInfo<>(pos);

        PageResult.Page page = new PageResult.Page(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal());
        List<TokenVO> vos = pos.stream().map(TokenVO::of).collect(Collectors.toList());
        return new PageResult<>(page, vos);
    }

    public void assignToken(Integer tokenId, Integer accountId) {
        // Check if account id and token id exist
        accountService.getAccountById(accountId);
        getTokenById(tokenId);

        AccountTokenRelPO accountTokenRelPO = new AccountTokenRelPO();
        accountTokenRelPO.setAccountId(accountId);
        accountTokenRelPO.setTokenId(tokenId);
        accountTokenRelMapper.insertAccountTokenRel(accountTokenRelPO);
    }

    private TokenPO getTokenById(Integer id) {
        TokenPO po = tokenMapper.getTokenById(id);
        Errors.NOT_FOUND.whenNull(po, id);
        return po;
    }

}
