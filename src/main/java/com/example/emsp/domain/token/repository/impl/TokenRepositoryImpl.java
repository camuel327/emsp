package com.example.emsp.domain.token.repository.impl;

import com.example.emsp.common.enums.Errors;
import com.example.emsp.common.enums.Status;
import com.example.emsp.domain.token.entity.Token;
import com.example.emsp.domain.token.infrastructure.mapper.TokenMapper;
import com.example.emsp.domain.token.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepository {

    private final TokenMapper tokenMapper;

    public Token getTokenById(int id) {
        Token po = tokenMapper.getTokenById(id);
        Errors.NOT_FOUND.whenNull(po, id);
        return po;
    }

    public void insertToken(Token po) {
        tokenMapper.insertToken(po);
    }

    public void updateStatusOfToken(Integer id, Status status) {
        tokenMapper.updateStatusOfToken(id, status);
    }

    public List<Token> pageTokens() {
        return tokenMapper.pageTokens();
    }

}
