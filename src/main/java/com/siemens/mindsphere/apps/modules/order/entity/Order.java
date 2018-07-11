package com.siemens.mindsphere.apps.modules.order.entity;

import com.siemens.mindsphere.apps.entity.BaseEntity;
import com.siemens.mindsphere.apps.modules.location.entity.Location;
import com.siemens.mindsphere.apps.modules.login.user.entity.User;
import com.siemens.mindsphere.apps.modules.product.entity.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "ORDER_DETAILS")
public class Order extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ORDER_PARAM_MAP",
            joinColumns = {@JoinColumn(name = "ORDER_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ORDER_PARAM_ID", referencedColumnName = "id")})
    private Set<OrderParams> orderParams;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "ORDER_PRODUCT_MAP",
            joinColumns = {@JoinColumn(name = "ORDER_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "id")})
    private Set<Product> products;

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

    public Set<OrderParams> getOrderParams() {
        return orderParams;
    }

    public void setOrderParams(Set<OrderParams> orderParams) {
        this.orderParams = orderParams;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

}
