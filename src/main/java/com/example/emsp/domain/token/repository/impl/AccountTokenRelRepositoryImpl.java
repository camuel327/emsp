package com.example.emsp.domain.token.repository.impl;

import com.example.emsp.domain.token.entity.AccountTokenRel;
import com.example.emsp.domain.token.infrastructure.mapper.AccountTokenRelMapper;
import com.example.emsp.domain.token.repository.AccountTokenRelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountTokenRelRepositoryImpl implements AccountTokenRelRepository {

    private final AccountTokenRelMapper accountTokenRelMapper;

    public void insertAccountTokenRel(AccountTokenRel po) {
        accountTokenRelMapper.insertAccountTokenRel(po);
    }

}
