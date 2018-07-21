package com.siemens.mindsphere.apps.modules.order.entity;

import com.siemens.mindsphere.apps.common.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ORDER_PARAM_MAP")
@IdClass(OrderParamPK.class)
public class OrderParamMapping extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "ORDER_DETAIL_ID")
    private Order order;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDER_PARAM_ID")
    private OrderParam orderParam;

    private String value;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderParam getOrderParam() {
        return orderParam;
    }

    public void setOrderParam(OrderParam orderParam) {
        this.orderParam = orderParam;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
