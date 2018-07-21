package com.siemens.mindsphere.apps.modules.product.entity;

import com.siemens.mindsphere.apps.common.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "PARAM_DETAIL")
public class ParamDetails extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARAM_DETAIL_ID")
    private Integer paramDetailId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_PARAM_ID")
    private ProductParams productParams;

    private String value;

    private String code;

    public ProductParams getProductParams() {
        return productParams;
    }

    public void setProductParams(ProductParams productParams) {
        this.productParams = productParams;
    }

    public Integer getParamDetailId() {
        return paramDetailId;
    }

    public void setParamDetailId(Integer paramDetailId) {
        this.paramDetailId = paramDetailId;
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
