package com.example.emsp.domain.account.repository.impl;

import com.example.emsp.common.enums.Errors;
import com.example.emsp.common.enums.Status;
import com.example.emsp.domain.account.entity.Account;
import com.example.emsp.domain.account.infrastructure.mapper.AccountMapper;
import com.example.emsp.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountMapper accountMapper;

    public Account getAccountById(int id) {
        Account po = accountMapper.getAccountById(id);
        Errors.NOT_FOUND.whenNull(po, id);
        return po;
    }

    public void insertAccount(Account po) {
        accountMapper.insertAccount(po);
    }

    public void updateStatusOfAccount(Integer id, Status status) {
        accountMapper.updateStatusOfAccount(id, status);
    }

    public List<Account> pageAccounts() {
        return accountMapper.pageAccounts();
    }

}
