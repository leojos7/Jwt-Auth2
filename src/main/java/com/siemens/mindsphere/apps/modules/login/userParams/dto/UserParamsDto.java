package com.siemens.mindsphere.apps.modules.login.userParams.dto;

public class UserParamsDto {

    private Integer userParamId;

    private String name;

    private String description;

    public UserParamsDto() {
    }

    public Integer getUserParamId() {
        return userParamId;
    }

    public void setUserParamId(Integer userParamId) {
        this.userParamId = userParamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
