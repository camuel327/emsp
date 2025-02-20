package com.example.emsp.strategy;

import com.example.emsp.model.enums.TokenType;
import com.example.emsp.model.form.CreateTokenForm;

public interface TokenTypeVerifyStrategy {

    TokenType supportTokenType();

    boolean verify(CreateTokenForm form);

}
