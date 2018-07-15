package com.siemens.mindsphere.apps.modules.order.entity;

import com.siemens.mindsphere.apps.entity.BaseEntity;
import com.siemens.mindsphere.apps.modules.location.entity.Location;
import com.siemens.mindsphere.apps.modules.login.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "ORDER_DETAIL")
public class Order extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_DETAIL_ID")
    private Integer orderDetailId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LOGIN_ID")
    private User loginId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LOCATION_ID")
    private Location locationId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDER_STATUS_ID")
    private OrderStatus orderStatusId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UPDATED_BY")
    private User updatedBy;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OrderParamMapping> orderParamMappings;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OrderProductMapping> orderProductMappings;

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public User getLoginId() {
        return loginId;
    }

    public void setLoginId(User loginId) {
        this.loginId = loginId;
    }

    public Location getLocationId() {
        return locationId;
    }

    public void setLocationId(Location locationId) {
        this.locationId = locationId;
    }

    public OrderStatus getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(OrderStatus orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Set<OrderParamMapping> getOrderParamMappings() {
        return orderParamMappings;
    }

    public void setOrderParamMappings(Set<OrderParamMapping> orderParamMappings) {
        this.orderParamMappings = orderParamMappings;
    }

    public Set<OrderProductMapping> getOrderProductMappings() {
        return orderProductMappings;
    }

    public void setOrderProductMappings(Set<OrderProductMapping> orderProductMappings) {
        this.orderProductMappings = orderProductMappings;
    }

}
