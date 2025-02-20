package com.example.emsp.service;

import com.example.emsp.model.enums.Status;
import com.example.emsp.model.form.CreateAccountForm;
import com.example.emsp.model.vo.AccountVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    @Transactional
    @Rollback()
    public void createAccount() {
        CreateAccountForm form = new CreateAccountForm();
        form.setServiceId(1);
        form.setFleetSolution("fleetSolution");
        form.setContractId("DEEON123456789");
        AccountVO account = accountService.createAccount(form);
        assertTrue(Objects.nonNull(account) &&
                Objects.nonNull(account.getId()) &&
                Objects.equals(Status.CREATED, account.getStatus()));
    }

}
