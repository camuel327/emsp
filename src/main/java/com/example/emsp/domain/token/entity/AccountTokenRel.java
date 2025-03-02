package com.example.emsp.domain.token.entity;

import lombok.Data;

import java.time.Instant;

@Data
public class AccountTokenRel {

    private Integer id;

    private Integer accountId;

    private Integer tokenId;

    private Instant createdAt;

    private Instant lastUpdated;

}
