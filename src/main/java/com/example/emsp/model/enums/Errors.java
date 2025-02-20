package com.example.emsp.model.enums;

import com.example.emsp.execption.BusinessException;
import com.example.emsp.model.resp.ResultBody;
import com.example.emsp.utils.I18nUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum Errors {

    NOT_FOUND(404, "Resource.Not.Found"),
    ILLEGAL_ARG(400, "Illegal.Arguments"),
    SYSTEM_ERROR(500, "System.Error"),
    ;

    Errors(Integer code, String pattern) {
        this.code = code;
        this.pattern = pattern;
    }

    private final Integer code;
    private final String pattern;


    public void whenNull(Object obj, Object... args) {
        if (obj == null) {
            exception(this.pattern, args);
        }
    }

    public void exceptionWhenFalse(boolean obj, String pattern, Object... args) {
        if (!obj) {
            exception(pattern, args);
        }
    }

    public void exception(String pattern, Object... args) {
        throw new BusinessException(this.code, this.format(pattern, args));
    }

    public ResultBody<Void> result(Object... args) {
        return ResultBody.failed(this.code, this.format(this.pattern, args));
    }

    private String format(String pattern, Object[] args) {
        String msg = I18nUtils.format(pattern, this.normalizeArgs(args));
        log.error(msg);
        return msg;
    }

    private Object[] normalizeArgs(Object[] args) {
        if (args == null) {
            return new Object[0];
        } else {
            for(int i = 0; i < args.length; ++i) {
                if (args[i] instanceof Number) {
                    args[i] = args[i].toString();
                }
            }
            return args;
        }
    }

}
