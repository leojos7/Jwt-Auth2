package com.siemens.mindsphere.apps.modules.order.entity;

import java.io.Serializable;
import java.util.Objects;

public class OrderParamPK implements Serializable {

    private Integer order;
    private Integer orderParam;

    public OrderParamPK() {
    }

    public OrderParamPK(Integer order, Integer orderParam) {
        this.order = order;
        this.orderParam = orderParam;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getOrderParam() {
        return orderParam;
    }

    public void setOrderParam(Integer orderParam) {
        this.orderParam = orderParam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderParamPK that = (OrderParamPK) o;
        return Objects.equals(order, that.order) &&
                Objects.equals(orderParam, that.orderParam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, orderParam);
    }
}
