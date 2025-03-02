package com.example.emsp.domain.token.infrastructure.mapper;

import com.example.emsp.common.enums.Status;
import com.example.emsp.domain.token.entity.Token;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TokenMapper {

    Token getTokenById(@Param("id") int id);

    void insertToken(Token po);

    void updateStatusOfToken(@Param("id") Integer id, @Param("status") Status status);

    List<Token> pageTokens();

}
