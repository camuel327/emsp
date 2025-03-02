package com.example.emsp.application.service;

import com.example.emsp.api.model.form.CreateTokenForm;
import com.example.emsp.api.model.form.UpdateTokenForm;
import com.example.emsp.api.model.resp.PageResult;
import com.example.emsp.api.model.vo.TokenVO;
import com.example.emsp.domain.token.entity.Token;
import com.example.emsp.domain.token.service.TokenDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenApplicationService {

    private final TokenDomainService tokenDomainService;

    public TokenVO createToken(CreateTokenForm form) {
        return TokenVO.of(tokenDomainService.createToken(form));
    }

    public TokenVO updateStatusOfToken(Integer tokenId, UpdateTokenForm form) {
        return TokenVO.of(tokenDomainService.updateStatusOfToken(tokenId, form));
    }

    public PageResult<TokenVO> searchTokens(Pageable pageable) {
        PageResult<Token> pageresult = tokenDomainService.searchTokens(pageable);
        return new PageResult<>(pageresult.getPage(), pageresult.getRows().stream().map(TokenVO::of).collect(Collectors.toList()));
    }

    public void assignToken(Integer tokenId, Integer accountId) {
        tokenDomainService.assignToken(tokenId, accountId);
    }

}
