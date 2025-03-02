package com.example.emsp.domain.token.infrastructure.mapper;

import com.example.emsp.domain.token.entity.AccountTokenRel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountTokenRelMapper {

    void insertAccountTokenRel(AccountTokenRel po);

}
