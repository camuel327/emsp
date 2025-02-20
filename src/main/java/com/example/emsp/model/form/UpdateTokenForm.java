package com.example.emsp.model.form;

import com.example.emsp.model.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Schema(description = "Update Token Form")
public class UpdateTokenForm {

    @NotNull
    @Schema(description = "Token Status")
    private Status status;

}
