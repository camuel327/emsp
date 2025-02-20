package com.example.emsp.model.form;

import com.example.emsp.model.po.AccountPO;
import com.example.emsp.model.vo.AccountVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.example.emsp.constant.constant.EMAID_REGX;

@Data
@Schema(description = "Create Account Form")
public class CreateAccountForm {

    @NotNull
    @Schema(description = "Service Id")
    private Integer serviceId;

    @NotBlank
    @Schema(description = "Fleet Solution", example = "Hubject")
    private String fleetSolution;

    @NotBlank
    @Pattern(regexp = EMAID_REGX)
    @Schema(description = "Contract Id", example = "DEEON123456789")
    private String contractId;

    public AccountPO toAccountPO() {
        AccountPO accountPO = new AccountPO();
        BeanUtils.copyProperties(this, accountPO);
        return accountPO;
    }

}
