package com.example.emsp.domain.token.strategy.impl;

import com.example.emsp.common.enums.TokenType;
import com.example.emsp.api.model.form.CreateTokenForm;
import com.example.emsp.domain.token.strategy.TokenTypeVerifyStrategy;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.example.emsp.common.constant.constant.FOUR_BYTES_RFID_REGX;

@Component
public class RFIDTokenTypeVerifyStrategy implements TokenTypeVerifyStrategy {

    @Override
    public TokenType supportTokenType() {
        return TokenType.RFID;
    }

    /*
    * 按照 4 字节 UID 校验
    * 示例：043F6A2B
    * */
    @Override
    public boolean verify(CreateTokenForm form) {
        return Objects.nonNull(form.getValueObject()) && form.getValueObject().matches(FOUR_BYTES_RFID_REGX);
    }

}
