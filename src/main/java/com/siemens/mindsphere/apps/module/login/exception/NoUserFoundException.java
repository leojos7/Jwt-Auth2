package com.siemens.mindsphere.apps.module.login.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoUserFoundException extends Exception {

    public NoUserFoundException(String message) {
        super(message);
    }
}
