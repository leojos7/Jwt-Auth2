package com.siemens.mindsphere.apps.module.login.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistingUserException extends Exception {

    public AlreadyExistingUserException(String message) {
        super(message);
    }
}
