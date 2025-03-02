package com.example.emsp.domain.token.service;

import com.example.emsp.api.model.form.CreateTokenForm;
import com.example.emsp.api.model.form.UpdateTokenForm;
import com.example.emsp.api.model.resp.PageResult;
import com.example.emsp.domain.token.entity.Token;
import org.springframework.data.domain.Pageable;

public interface TokenDomainService {

    Token createToken(CreateTokenForm form);

    Token updateStatusOfToken(Integer tokenId, UpdateTokenForm form);

    PageResult<Token> searchTokens(Pageable pageable);

    void assignToken(Integer tokenId, Integer accountId);

}
