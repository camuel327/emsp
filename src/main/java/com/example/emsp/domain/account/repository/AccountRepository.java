package com.example.emsp.domain.account.repository;

import com.example.emsp.common.enums.Status;
import com.example.emsp.domain.account.entity.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountRepository {

    Account getAccountById(@Param("id") int id);

    void insertAccount(Account po);

    void updateStatusOfAccount(@Param("id") Integer id, @Param("status") Status status);

    List<Account> pageAccounts();

}
