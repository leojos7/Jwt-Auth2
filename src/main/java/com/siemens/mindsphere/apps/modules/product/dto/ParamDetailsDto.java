package com.siemens.mindsphere.apps.modules.product.dto;

import java.util.Date;

public class ParamDetailsDto {

    private Integer paramDetailId;

    private ProductParamsDto productParams;

    private String value;

    private String code;

    private Date createDate;

    private Date modifiedDate;

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
