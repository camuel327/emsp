package com.example.emsp.model.po;

import lombok.Data;

import java.time.Instant;

@Data
public class AccountTokenRelPO {

    private Integer id;

    private Integer accountId;

    private Integer tokenId;

    private Instant createdAt;

    private Instant lastUpdated;

}
