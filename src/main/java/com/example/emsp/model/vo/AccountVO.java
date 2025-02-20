package com.example.emsp.model.vo;

import com.example.emsp.model.enums.Status;
import com.example.emsp.model.po.AccountPO;
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

    @Schema(description = "contractId", example = "DEEON12345678A")
    private String contractId;

    @Schema(description = "status", example = "0")
    private Status status;

    private Instant createdAt;

    private Instant lastUpdated;

    public static AccountVO of(AccountPO source) {
        AccountVO accountVO = new AccountVO();
        BeanUtils.copyProperties(source, accountVO);
        return accountVO;
    }

}
