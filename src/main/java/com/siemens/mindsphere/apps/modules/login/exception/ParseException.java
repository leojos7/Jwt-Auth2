package com.siemens.mindsphere.apps.modules.login.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParseException extends RuntimeException {

    public ParseException(String message) {
        super(message);
    }
}
