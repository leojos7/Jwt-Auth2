package com.siemens.mindsphere.apps.module.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyExistingProductException extends Exception {

    public AlreadyExistingProductException(String message) {
        super(message);
    }
}
