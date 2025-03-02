package com.example.emsp.service;

import com.example.emsp.api.model.form.CreateAccountForm;
import com.example.emsp.common.enums.Status;
import com.example.emsp.domain.account.entity.Account;
import com.example.emsp.domain.account.service.AccountDomainServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AccountDomainServiceImplTest {

    @Autowired
    private AccountDomainServiceImpl accountDomainService;

    @Test
    @Transactional
    @Rollback()
    public void createAccount() {
        CreateAccountForm form = new CreateAccountForm();
        form.setServiceId(1);
        form.setFleetSolution("fleetSolution");
        form.setContractId("DEEON123456789");
        Account account = accountDomainService.createAccount(form);
        assertTrue(Objects.nonNull(account) &&
                Objects.nonNull(account.getId()) &&
                Objects.equals(Status.CREATED, account.getStatus()));
    }

}
