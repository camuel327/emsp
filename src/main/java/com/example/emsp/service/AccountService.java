package com.example.emsp.service;

import com.example.emsp.mapper.AccountMapper;
import com.example.emsp.model.enums.Errors;
import com.example.emsp.model.enums.Status;
import com.example.emsp.model.form.CreateAccountForm;
import com.example.emsp.model.form.UpdateAccountForm;
import com.example.emsp.model.po.AccountPO;
import com.example.emsp.model.resp.PageResult;
import com.example.emsp.model.vo.AccountVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountMapper accountMapper;

    public AccountVO createAccount(CreateAccountForm form) {
        AccountPO accountPO = form.toAccountPO();
        accountPO.setStatus(Status.CREATED);
        Instant now = Instant.now();
        accountPO.setCreatedAt(now);
        accountPO.setLastUpdated(now);
        accountMapper.insertAccount(accountPO);
        return AccountVO.of(accountPO);
    }

    public AccountVO updateStatusOfAccount(Integer accountId, UpdateAccountForm form) {
        AccountPO accountPO = getAccountById(accountId);
        Status updatedStatus = form.getStatus();

        Errors.ILLEGAL_ARG.exceptionWhenFalse(!Objects.equals(Status.CREATED, updatedStatus),
                "Updated.Status.Can.Not.Be.Created");

        if (!Objects.equals(accountPO.getStatus(), updatedStatus)) {
            accountPO.setStatus(updatedStatus);
            accountMapper.updateStatusOfAccount(accountId, updatedStatus);

        }
        return AccountVO.of(accountPO);
    }

    public PageResult<AccountVO> searchAccounts(Pageable pageable) {
        int pageNum = pageable.getPageNumber() + 1;
        int pageSize = pageable.getPageSize();
        String orderBy = pageable.getSort().toString().replace(":", " ");

        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<AccountPO> pos = accountMapper.pageAccounts();
        PageInfo<AccountPO> pageInfo = new PageInfo<>(pos);

        PageResult.Page page = new PageResult.Page(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal());
        List<AccountVO> vos = pos.stream().map(AccountVO::of).collect(Collectors.toList());
        return new PageResult<>(page, vos);
    }

    public AccountPO getAccountById(Integer id) {
        AccountPO po = accountMapper.getAccountById(id);
        Errors.NOT_FOUND.whenNull(po, id);
        return po;
    }

}
