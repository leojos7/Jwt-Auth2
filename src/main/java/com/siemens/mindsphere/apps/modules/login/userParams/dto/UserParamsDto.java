package com.siemens.mindsphere.apps.modules.login.userParams.dto;

import java.util.Date;

public class UserParamsDto {

    private Integer userParamId;

    private String name;

    private String description;

    private Date createDate;

    private Date modifiedDate;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
