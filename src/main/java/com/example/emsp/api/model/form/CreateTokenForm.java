package com.example.emsp.api.model.form;

import com.example.emsp.common.enums.TokenType;
import com.example.emsp.domain.token.entity.Token;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

@Data
@Schema(description = "Create Token Form")
public class CreateTokenForm {

    @NotNull
    @Schema(description = "type")
    private TokenType type;

    @NotNull
    @Schema(description = "value object", example = "{\"countryCode\":\"DE\",\"providerId\":\"EON\",\"instanceId\":\"123456789\"}")
    private String valueObject;

    public Token toToken() {
        Token token = new Token();
        BeanUtils.copyProperties(this, token);
        token.setValue(valueObject);
        return token;
    }

}
