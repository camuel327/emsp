package com.example.emsp.model.form;

import com.example.emsp.model.enums.TokenType;
import com.example.emsp.model.po.TokenPO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "Create Token Form")
public class CreateTokenForm {

    @NotNull
    @Schema(description = "type")
    private TokenType type;

    @NotBlank
    @Schema(description = "value", example = "DEEON123456789")
    private String value;

    public TokenPO toTokenPO() {
        TokenPO tokenPO = new TokenPO();
        BeanUtils.copyProperties(this, tokenPO);
        return tokenPO;
    }

}
