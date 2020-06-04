package com.wyf.myblog.exceptionsolve;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author wyf
 * @create 2020/5/24
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
