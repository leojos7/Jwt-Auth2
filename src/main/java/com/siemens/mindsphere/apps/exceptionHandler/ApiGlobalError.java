package com.siemens.mindsphere.apps.exceptionHandler;

public class ApiGlobalError {

    private String code;

    public ApiGlobalError() {
        super();
    }

    public ApiGlobalError(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
