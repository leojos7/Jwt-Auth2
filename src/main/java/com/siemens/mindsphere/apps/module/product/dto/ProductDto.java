package com.siemens.mindsphere.apps.module.product.dto;

import com.siemens.mindsphere.apps.module.login.dto.UserDto;

import java.util.Set;

public class ProductDto {


    private Integer id;

    private String name;

    private String description;

    private UserDto addedBy;

    private String code;

    private Boolean status;

    private Set<ParamDetailsDto> paramDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public UserDto getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(UserDto addedBy) {
        this.addedBy = addedBy;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Set<ParamDetailsDto> getParamDetails() {
        return paramDetails;
    }

    public void setParamDetails(Set<ParamDetailsDto> paramDetails) {
        this.paramDetails = paramDetails;
    }
}
