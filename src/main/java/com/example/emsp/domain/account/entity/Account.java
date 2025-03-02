package com.example.emsp.domain.account.entity;

import com.example.emsp.common.enums.Status;
import lombok.Data;

import java.time.Instant;

@Data
public class Account {

    private Integer id;

    private Integer serviceId;

    private String fleetSolution;

    private String contractId;

    private Status status;

    private Instant createdAt;

    private Instant lastUpdated;

}
