package com.siemens.mindsphere.apps.modules.product.dto;

public class ProductParamsDto {

    private Integer productParamId;

    private String name;

    private String description;

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
}
