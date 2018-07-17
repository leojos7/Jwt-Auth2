package com.siemens.mindsphere.apps.exception;

public class ExceptionResponseImpl implements ExceptionResponse {

    private Long errorCode;
    private String errorMessage;

    public ExceptionResponseImpl() {
    }

    public Long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
