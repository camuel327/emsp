package com.example.emsp.domain.token.strategy;

import com.example.emsp.common.enums.TokenType;
import com.example.emsp.api.model.form.CreateTokenForm;

public interface TokenTypeVerifyStrategy {

    TokenType supportTokenType();

    boolean verify(CreateTokenForm form);

}
