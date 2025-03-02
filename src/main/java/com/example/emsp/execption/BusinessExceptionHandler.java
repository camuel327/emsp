package com.example.emsp.execption;

import com.example.emsp.api.model.resp.ResultBody;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(-65536)
public class BusinessExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    public ResultBody<Void> handler(BusinessException ex) {
        return ResultBody.failed(ex.getCode(), ex.getMessage());
    }

}
