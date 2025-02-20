package com.example.emsp.mapper;

import com.example.emsp.model.enums.Status;
import com.example.emsp.model.po.TokenPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenMapper {

    TokenPO getTokenById(@Param("id") int id);

    void insertToken(TokenPO po);

    void updateStatusOfToken(@Param("id") Integer id, @Param("status") Status status);

    List<TokenPO> pageTokens();

}
