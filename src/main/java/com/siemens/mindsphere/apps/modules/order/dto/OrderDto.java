package com.siemens.mindsphere.apps.modules.order.dto;

import com.siemens.mindsphere.apps.modules.login.user.dto.UserDto;

import java.util.Date;
import java.util.Set;

public class OrderDto {

    private Integer id;

    private UserDto loginId;

    private Integer locationId;

    private Integer orderStatusId;

    private UserDto updatedBy;

    private Set<OrderParamDto> orderParams;

    private Set<ProductDetailsDto> products;

    private Date createDate;

    private Date modifiedDate;

    public OrderDto() {
    }

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

    public Set<OrderParamDto> getOrderParams() {
        return orderParams;
    }

    public void setOrderParams(Set<OrderParamDto> orderParams) {
        this.orderParams = orderParams;
    }

    public Set<ProductDetailsDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDetailsDto> products) {
        this.products = products;
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
