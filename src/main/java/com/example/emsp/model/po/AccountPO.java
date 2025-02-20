package com.example.emsp.model.po;

import com.example.emsp.model.enums.Status;
import lombok.Data;

import java.time.Instant;

@Data
public class AccountPO {

    private Integer id;

    private Integer serviceId;

    private String fleetSolution;

    private String contractId;

    private Status status;

    private Instant createdAt;

    private Instant lastUpdated;

}
