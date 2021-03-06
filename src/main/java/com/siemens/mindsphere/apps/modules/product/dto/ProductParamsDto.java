package com.siemens.mindsphere.apps.modules.product.dto;

import java.util.Date;

public class ProductParamsDto {

    private Integer productParamId;

    private String name;

    private String description;

    private Date createDate;

    private Date modifiedDate;

    public ProductParamsDto() {
    }

    public Integer getProductParamId() {
        return productParamId;
    }

    public void setProductParamId(Integer productParamId) {
        this.productParamId = productParamId;
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
