package com.example.emsp.domain.account.service.Impl;

import com.example.emsp.api.model.form.CreateAccountForm;
import com.example.emsp.api.model.form.UpdateAccountForm;
import com.example.emsp.api.model.resp.PageResult;
import com.example.emsp.domain.account.entity.Account;
import org.springframework.data.domain.Pageable;

public interface AccountDomainService {

    Account createAccount(CreateAccountForm form);

    Account updateStatusOfAccount(Integer accountId, UpdateAccountForm form);

    PageResult<Account> searchAccounts(Pageable pageable);

    Account getAccountById(Integer id);

}
