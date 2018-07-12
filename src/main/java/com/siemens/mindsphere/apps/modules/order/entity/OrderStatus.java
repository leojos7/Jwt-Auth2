package com.siemens.mindsphere.apps.modules.order.entity;

import com.siemens.mindsphere.apps.entity.BaseParamsEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "ORDER_STATUS")
public class OrderStatus extends BaseParamsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_STATUS_ID")
    private Integer orderStatusId;

    public Integer getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(Integer orderStatusId) {
        this.orderStatusId = orderStatusId;
    }
}
