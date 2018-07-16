package com.siemens.mindsphere.apps.modules.product.dto;

import com.siemens.mindsphere.apps.modules.login.user.dto.UserDto;

import java.util.Set;

public class ProductDto {


    private Integer productId;

    private String name;

    private String description;

    private UserDto addedBy;

    private String code;

    private Boolean status;

    private Set<ParamDetailsDto> paramDetails;

    public ProductDto() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
