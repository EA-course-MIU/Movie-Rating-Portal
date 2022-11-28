package com.edu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalException extends RuntimeException {
    public InternalException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public InternalException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalException(Throwable cause) {
        super(cause);
    }
}
