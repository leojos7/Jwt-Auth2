package com.siemens.mindsphere.apps.modules.order.entity;

import java.io.Serializable;
import java.util.Objects;

public class OrderProductPK implements Serializable {

    private Integer order;
    private Integer product;

    public OrderProductPK() {
    }

    public OrderProductPK(Integer order, Integer product) {
        this.order = order;
        this.product = product;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProductPK that = (OrderProductPK) o;
        return Objects.equals(order, that.order) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {

        return Objects.hash(order, product);
    }
}
