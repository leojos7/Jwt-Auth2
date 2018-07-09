package com.siemens.mindsphere.apps.module.order.entity;

import com.siemens.mindsphere.apps.module.location.entity.Location;
import com.siemens.mindsphere.apps.module.login.entity.User;
import com.siemens.mindsphere.apps.module.product.entity.Product;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "ORDER_DETAILS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
