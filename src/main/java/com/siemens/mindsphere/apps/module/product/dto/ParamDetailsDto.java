package com.siemens.mindsphere.apps.module.product.dto;

public class ParamDetailsDto {

    private Integer id;

    private ProductParamsDto productParams;

    private String value;

    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductParamsDto getProductParams() {
        return productParams;
    }

    public void setProductParams(ProductParamsDto productParams) {
        this.productParams = productParams;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
