package com.siemens.mindsphere.apps.module.order.dto;

import com.siemens.mindsphere.apps.module.login.dto.UserDto;
import com.siemens.mindsphere.apps.module.product.dto.ProductDto;

import java.util.Set;

public class OrderDto {

    private Integer id;

    private UserDto loginId;

    private Integer locationId;

    private Integer orderStatusId;

    private UserDto updatedBy;

    private Set<OrderParamsDto> orderParams;

    private Set<ProductDto> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDto getLoginId() {
        return loginId;
    }

    public void setLoginId(UserDto loginId) {
        this.loginId = loginId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(Integer orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public UserDto getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(UserDto updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Set<OrderParamsDto> getOrderParams() {
        return orderParams;
    }

    public void setOrderParams(Set<OrderParamsDto> orderParams) {
        this.orderParams = orderParams;
    }

    public Set<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDto> products) {
        this.products = products;
    }
}
