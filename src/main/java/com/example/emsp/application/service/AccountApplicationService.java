package com.example.emsp.application.service;

import com.example.emsp.api.model.form.CreateAccountForm;
import com.example.emsp.api.model.form.UpdateAccountForm;
import com.example.emsp.api.model.resp.PageResult;
import com.example.emsp.api.model.vo.AccountVO;
import com.example.emsp.domain.account.entity.Account;
import com.example.emsp.domain.account.service.Impl.AccountDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountApplicationService {

    private final AccountDomainService accountDomainService;

    public AccountVO createAccount(CreateAccountForm form) {
        return AccountVO.of(accountDomainService.createAccount(form));
    }

    public AccountVO updateStatusOfAccount(Integer accountId, UpdateAccountForm form) {
        return AccountVO.of(accountDomainService.updateStatusOfAccount(accountId, form));
    }

    public PageResult<AccountVO> searchAccounts(Pageable pageable) {
        PageResult<Account> pageresult = accountDomainService.searchAccounts(pageable);
        return new PageResult<>(pageresult.getPage(), pageresult.getRows().stream().map(AccountVO::of).collect(Collectors.toList()));
    }

}
