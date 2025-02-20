package com.example.emsp.execption;

import com.example.emsp.model.enums.Errors;
import com.example.emsp.model.resp.ResultBody;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(65536)
public class GenericExceptionHandlers {

    @ExceptionHandler({Exception.class})
    public ResultBody<Void> handler(Exception ex) {
        return Errors.SYSTEM_ERROR.result(ex.getMessage());
    }

}
