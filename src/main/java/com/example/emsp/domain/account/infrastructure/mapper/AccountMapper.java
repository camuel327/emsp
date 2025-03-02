package com.example.emsp.domain.account.infrastructure.mapper;

import com.example.emsp.common.enums.Status;
import com.example.emsp.domain.account.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AccountMapper {

    Account getAccountById(@Param("id") int id);

    void insertAccount(Account po);

    void updateStatusOfAccount(@Param("id") Integer id, @Param("status") Status status);

    List<Account> pageAccounts();

}
