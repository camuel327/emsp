package com.example.emsp.domain.token.strategy.impl;

import com.example.emsp.api.model.object.EMAID;
import com.example.emsp.common.enums.TokenType;
import com.example.emsp.api.model.form.CreateTokenForm;
import com.example.emsp.domain.token.strategy.TokenTypeVerifyStrategy;
import com.example.emsp.utils.JacksonUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.example.emsp.common.constant.constant.*;

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
        return Objects.nonNull(form.getValueObject()) && isValidEMAID(form.getValueObject());
    }

    private boolean isValidEMAID(String emaidStr) {
        try {
           EMAID emaid = JacksonUtils.parse(emaidStr, EMAID.class);
           return emaid.getCountryCode().matches(EMAID_COUNTRY_CODE_REGX) &&
                   emaid.getProviderId().matches(EMAID_PROVIDER_ID_REGX) &&
                   emaid.getInstanceId().matches(EMAID_INSTANCE_ID_REGX);
        } catch (Exception e) {
            return false;
        }
    }

}
