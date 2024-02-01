package com.example.practice.project.customexception;

import org.springframework.security.core.AuthenticationException;

public class InvalidGrantException extends AuthenticationException {
    public InvalidGrantException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InvalidGrantException(String msg) {
        super(msg);
    }
}
