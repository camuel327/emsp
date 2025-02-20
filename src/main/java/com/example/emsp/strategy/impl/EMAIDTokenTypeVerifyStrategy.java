package com.example.emsp.strategy.impl;

import com.example.emsp.model.enums.TokenType;
import com.example.emsp.model.form.CreateTokenForm;
import com.example.emsp.strategy.TokenTypeVerifyStrategy;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.example.emsp.constant.constant.EMAID_REGX;

@Component
public class EMAIDTokenTypeVerifyStrategy implements TokenTypeVerifyStrategy {

    @Override
    public TokenType supportTokenType() {
        return TokenType.EMAID;
    }

    /*
    * 按照 <EMAID> = <Country Code><Provider ID><eMA Instance> 校验
    * 示例：DEEON123456789
    * */
    @Override
    public boolean verify(CreateTokenForm form) {
        return Objects.nonNull(form.getValue()) && form.getValue().matches(EMAID_REGX);
    }

}
