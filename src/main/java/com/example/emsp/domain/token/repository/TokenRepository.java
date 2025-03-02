package com.example.emsp.domain.token.repository;

import com.example.emsp.common.enums.Status;
import com.example.emsp.domain.token.entity.Token;

import java.util.List;

public interface TokenRepository {

    Token getTokenById(int id);

    void insertToken(Token po);

    void updateStatusOfToken(Integer id, Status status);

    List<Token> pageTokens();

}
