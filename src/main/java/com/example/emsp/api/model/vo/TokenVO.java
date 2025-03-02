package com.example.emsp.api.model.vo;

import com.example.emsp.api.model.object.EMAID;
import com.example.emsp.common.enums.Status;
import com.example.emsp.common.enums.TokenType;
import com.example.emsp.domain.token.entity.Token;
import com.example.emsp.utils.JacksonUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.Instant;
import java.util.Objects;

@Data
@Schema(description = "TokenVO")
public class TokenVO {

    @Schema(description = "id", example = "1")
    private Integer id;

    @Schema(description = "status", example = "0")
    private Status status;

    @Schema(description = "type", example = "0")
    private TokenType type;

    @JsonIgnore
    private String value;

    @Schema(description = "value object", example = "127.0.0.1")
    private Object valueObject;

    private Instant createdAt;

    private Instant lastUpdated;

    public static TokenVO of(Token source) {
        TokenVO tokenVO = new TokenVO();
        BeanUtils.copyProperties(source, tokenVO);
        if (Objects.equals(TokenType.EMAID, source.getType())) {
            tokenVO.setValueObject(JacksonUtils.parse(source.getValue(), EMAID.class));
        } else {
            tokenVO.setValueObject(source.getValue());
        }
        return tokenVO;
    }

}
