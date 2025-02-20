package com.example.emsp.model.po;

import com.example.emsp.model.enums.Status;
import com.example.emsp.model.enums.TokenType;
import lombok.Data;

import java.time.Instant;

@Data
public class TokenPO {

    private Integer id;

    private Status status;

    private TokenType type;

    private String value;

    private Instant createdAt;

    private Instant lastUpdated;

}
