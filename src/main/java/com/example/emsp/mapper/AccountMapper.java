package com.example.emsp.mapper;

import com.example.emsp.model.enums.Status;
import com.example.emsp.model.po.AccountPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapper {

    AccountPO getAccountById(@Param("id") int id);

    void insertAccount(AccountPO po);

    void updateStatusOfAccount(@Param("id") Integer id, @Param("status") Status status);

    List<AccountPO> pageAccounts();

}
