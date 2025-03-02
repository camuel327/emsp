package com.example.emsp.service;

import com.example.emsp.domain.token.service.TokenDomainService;
import com.example.emsp.execption.BusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@SpringBootTest
public class TokenDomainServiceTest {

    @Autowired
    private TokenDomainService tokenDomainService;

    @Test
    @Transactional
    @Rollback()
    public void assignToken() {
        // Not Found
        assertThrowsExactly(BusinessException.class, () -> tokenDomainService.assignToken(0, 0));
    }

}
