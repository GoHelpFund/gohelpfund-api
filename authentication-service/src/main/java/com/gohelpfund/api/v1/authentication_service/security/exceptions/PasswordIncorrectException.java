package com.gohelpfund.api.v1.authentication_service.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class PasswordIncorrectException extends AuthenticationException {
    public PasswordIncorrectException(String msg) {
        super(msg);
    }

    public PasswordIncorrectException(String msg, Throwable t) {
        super(msg, t);
    }
}
