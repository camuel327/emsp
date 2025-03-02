package com.example.emsp.api.model.object;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.example.emsp.common.constant.constant.*;

@Data
public class EMAID {

    @NotBlank
    @Pattern(regexp = EMAID_COUNTRY_CODE_REGX)
    @Schema(description = "Country Code", example = "DE")
    private String countryCode;

    @NotBlank
    @Pattern(regexp = EMAID_PROVIDER_ID_REGX)
    @Schema(description = "Provider ID", example = "EON")
    private String providerId;

    @NotBlank
    @Pattern(regexp = EMAID_INSTANCE_ID_REGX)
    @Schema(description = "Instance ID", example = "123456789")
    private String instanceId;

}
