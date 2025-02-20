package com.example.emsp.model.form;

import com.example.emsp.model.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Schema(description = "Update Account Form")
public class UpdateAccountForm {

    @NotNull
    @Schema(description = "Account Status")
    private Status status;

}
