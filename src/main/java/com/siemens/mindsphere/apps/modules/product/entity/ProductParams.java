package com.siemens.mindsphere.apps.modules.product.entity;

import com.siemens.mindsphere.apps.entity.BaseParamsEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "PRODUCT_PARAM")
public class ProductParams extends BaseParamsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_PARAM_ID")
    private Integer productParamId;

    public Integer getProductParamId() {
        return productParamId;
    }

    public void setProductParamId(Integer productParamId) {
        this.productParamId = productParamId;
    }
}
