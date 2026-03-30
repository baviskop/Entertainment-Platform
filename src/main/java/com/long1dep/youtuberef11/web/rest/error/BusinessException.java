package com.long1dep.youtuberef11.web.rest.error;


import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
public class BusinessException extends RuntimeException{
    private final String errorCode;
    private final String message;

    public BusinessException(String msg) {
        super(msg);
        this.errorCode = Strings.EMPTY;
        this.message = msg;
    }

    public BusinessException(String errorCode, String msg, Throwable ex) {
        super(msg, ex);
        this.errorCode = errorCode;
        this.message = msg;
    }

    public BusinessException(String errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
        this.message = msg;
    }
}
