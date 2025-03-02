package com.example.emsp.api.model.vo;

import com.example.emsp.api.model.object.EMAID;
import com.example.emsp.common.enums.Status;
import com.example.emsp.domain.account.entity.Account;
import com.example.emsp.utils.JacksonUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.Instant;

@Data
@Schema(description = "AccountVO")
public class AccountVO {

    @Schema(description = "id", example = "1")
    private Integer id;

    @Schema(description = "serviceId", example = "1")
    private Integer serviceId;

    @Schema(description = "fleetSolution", example = "Hubject")
    private String fleetSolution;

    @JsonIgnore
    private String contractId;

    @Schema(description = "Contract ID Info")
    private EMAID contractIdInfo;

    @Schema(description = "status", example = "0")
    private Status status;

    private Instant createdAt;

    private Instant lastUpdated;

    public static AccountVO of(Account source) {
        AccountVO accountVO = new AccountVO();
        BeanUtils.copyProperties(source, accountVO);
        accountVO.setContractIdInfo(JacksonUtils.parse(source.getContractId(), EMAID.class));
        return accountVO;
    }

}
