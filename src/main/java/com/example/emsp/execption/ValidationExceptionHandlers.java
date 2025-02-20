package com.example.emsp.execption;

import com.example.emsp.model.enums.Errors;
import com.example.emsp.model.resp.ResultBody;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.Iterator;

@RestControllerAdvice
@Order(-1024)
public class ValidationExceptionHandlers {

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class,
            ConstraintViolationException.class,
    })
    public ResultBody<Void> handler(Exception ex) {
        return Errors.ILLEGAL_ARG.result(ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultBody<Void> handler(MethodArgumentNotValidException ex) {
        StringBuilder buf = new StringBuilder();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            buf.append(resolveField(error)).append(": ").append(error.getDefaultMessage()).append("; ");
        }

        return Errors.ILLEGAL_ARG.result(buf.toString());
    }

    private static String resolveField(ObjectError error) {
        return error instanceof FieldError ? ((FieldError)error).getField() : error.getObjectName();
    }

}
