package com.example.emsp.strategy.impl;

import com.example.emsp.model.enums.TokenType;
import com.example.emsp.model.form.CreateTokenForm;
import com.example.emsp.strategy.TokenTypeVerifyStrategy;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.example.emsp.constant.constant.FOUR_BYTES_RFID_REGX;

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
        return Objects.nonNull(form.getValue()) && form.getValue().matches(FOUR_BYTES_RFID_REGX);
    }

}
