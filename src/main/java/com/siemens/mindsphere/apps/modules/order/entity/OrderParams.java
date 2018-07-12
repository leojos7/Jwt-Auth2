package com.siemens.mindsphere.apps.modules.order.entity;

import com.siemens.mindsphere.apps.entity.BaseParamsEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "ORDER_PARAM")
public class OrderParams extends BaseParamsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_PARAM_ID")
    private Integer orderParamId;

    public Integer getOrderParamId() {
        return orderParamId;
    }

    public void setOrderParamId(Integer orderParamId) {
        this.orderParamId = orderParamId;
    }
}
