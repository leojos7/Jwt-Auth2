package com.siemens.mindsphere.apps.exception;

public class ValidationExceptionResponseImpl implements ExceptionResponse {

    private String field;
    private String code;

    public ValidationExceptionResponseImpl() {
    }

    public ValidationExceptionResponseImpl(String field, String code) {
        this.field = field;
        this.code = code;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
