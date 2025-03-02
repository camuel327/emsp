package com.example.emsp.domain.account.service;

import com.example.emsp.api.model.form.CreateAccountForm;
import com.example.emsp.api.model.form.UpdateAccountForm;
import com.example.emsp.api.model.resp.PageResult;
import com.example.emsp.common.enums.Errors;
import com.example.emsp.common.enums.Status;
import com.example.emsp.domain.account.entity.Account;
import com.example.emsp.domain.account.repository.AccountRepository;
import com.example.emsp.domain.account.service.Impl.AccountDomainService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountDomainServiceImpl implements AccountDomainService {

    private final AccountRepository accountRepository;

    public Account createAccount(CreateAccountForm form) {
        Account account = form.toAccount();
        account.setStatus(Status.CREATED);
        Instant now = Instant.now();
        account.setCreatedAt(now);
        account.setLastUpdated(now);
        accountRepository.insertAccount(account);
        return account;
    }

    public Account updateStatusOfAccount(Integer accountId, UpdateAccountForm form) {
        Account account = accountRepository.getAccountById(accountId);
        Status updatedStatus = form.getStatus();

        Errors.ILLEGAL_ARG.exceptionWhenFalse(!Objects.equals(Status.CREATED, updatedStatus),
                "Updated.Status.Can.Not.Be.Created");

        if (!Objects.equals(account.getStatus(), updatedStatus)) {
            account.setStatus(updatedStatus);
            accountRepository.updateStatusOfAccount(accountId, updatedStatus);

        }
        return account;
    }

    public PageResult<Account> searchAccounts(Pageable pageable) {
        int pageNum = pageable.getPageNumber() + 1;
        int pageSize = pageable.getPageSize();
        String orderBy = pageable.getSort().toString().replace(":", " ");

        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Account> pos = accountRepository.pageAccounts();
        PageInfo<Account> pageInfo = new PageInfo<>(pos);

        PageResult.Page page = new PageResult.Page(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal());

        return new PageResult<>(page, pos);
    }

    public Account getAccountById(Integer id) {
        return accountRepository.getAccountById(id);
    }

}
