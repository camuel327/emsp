package com.example.emsp.domain.token.strategy.impl;

import com.example.emsp.common.enums.TokenType;
import com.example.emsp.api.model.form.CreateTokenForm;
import com.example.emsp.domain.token.strategy.TokenTypeVerifyStrategy;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

import static com.example.emsp.common.constant.constant.IPV4_PATTERN;

@Component
public class MacAddressTokenTypeVerifyStrategy implements TokenTypeVerifyStrategy {

    @Override
    public TokenType supportTokenType() {
        return TokenType.MAC_ADDRESS;
    }

    @Override
    public boolean verify(CreateTokenForm form) {
        return Objects.nonNull(form.getValueObject()) &&
                form.getValueObject().matches(IPV4_PATTERN) || isValidIPv6(form.getValueObject());
    }

    private boolean isValidIPv6(String ipAddress) {
        try {
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            return inetAddress instanceof java.net.Inet6Address;
        } catch (UnknownHostException e) {
            return false;
        }
    }

}
