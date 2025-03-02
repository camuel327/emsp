package com.example.emsp.api.model.form;

import com.example.emsp.api.model.object.EMAID;
import com.example.emsp.domain.account.entity.Account;
import com.example.emsp.utils.JacksonUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "Create Account Form")
public class CreateAccountForm {

    @NotNull
    @Schema(description = "Service Id")
    private Integer serviceId;

    @NotBlank
    @Schema(description = "Fleet Solution", example = "Hubject")
    private String fleetSolution;

    @NotNull
    @Valid
    @Schema(description = "Contract ID Info")
    private EMAID contractIdInfo;

    public Account toAccount() {
        Account account = new Account();
        BeanUtils.copyProperties(this, account);
        account.setContractId(JacksonUtils.toString(contractIdInfo));
        return account;
    }

}
