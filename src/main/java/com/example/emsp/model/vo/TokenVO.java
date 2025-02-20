package com.example.emsp.model.vo;

import com.example.emsp.model.enums.Status;
import com.example.emsp.model.enums.TokenType;
import com.example.emsp.model.po.TokenPO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.Instant;

@Data
@Schema(description = "TokenVO")
public class TokenVO {

    @Schema(description = "id", example = "1")
    private Integer id;

    @Schema(description = "status", example = "0")
    private Status status;

    @Schema(description = "type", example = "0")
    private TokenType type;

    @Schema(description = "value", example = "DEEON12345678A")
    private String value;

    private Instant createdAt;

    private Instant lastUpdated;

    public static TokenVO of(TokenPO source) {
        TokenVO tokenVO = new TokenVO();
        BeanUtils.copyProperties(source, tokenVO);
        return tokenVO;
    }

}
