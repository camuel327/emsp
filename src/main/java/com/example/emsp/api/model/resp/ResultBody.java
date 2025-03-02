package com.example.emsp.api.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Result Body")
public class ResultBody<T> {

    @Schema(description = "success")
    private final boolean success;

    @Schema(description = "code")
    private final Integer code;

    @Schema(description = "message")
    private final String message;

    @Schema(description = "data")
    private final T data;

    public static <T> ResultBody<T> success(String message, T data) {
        return new ResultBody<T>(true, 200, message, data);
    }

    public static <T> ResultBody<T> success(T data) {
        return success(null, data);
    }

    public static <T> ResultBody<T> success() {
        return success(null, null);
    }

    public static <T> ResultBody<T> failed(Integer code, String message) {
        return new ResultBody<T>(false, code, message, null);
    }

}
