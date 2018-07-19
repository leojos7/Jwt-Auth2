package com.siemens.mindsphere.apps.modules.product.dto;

public class ParamDetailsDto {

    private Integer paramDetailId;

    private ProductParamsDto productParams;

    private String value;

    private String code;

    public ParamDetailsDto() {
    }

    public Integer getParamDetailId() {
        return paramDetailId;
    }

    public void setParamDetailId(Integer paramDetailId) {
        this.paramDetailId = paramDetailId;
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
