package com.example.emsp.domain.token.entity;

import com.example.emsp.common.enums.Status;
import com.example.emsp.common.enums.TokenType;
import lombok.Data;

import java.time.Instant;

@Data
public class Token {

    private Integer id;

    private Status status;

    private TokenType type;

    private String value;

    private Instant createdAt;

    private Instant lastUpdated;

}
