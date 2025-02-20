package com.example.emsp.execption;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    private final Integer code;
    private final String message;

    public BusinessException(Integer code, String message, Throwable cause) {
        super(message, cause, true, true);
        this.code = code;
        this.message = message;
    }

    public BusinessException(Integer code, String message) {
        this(code, message, null);
    }

}
