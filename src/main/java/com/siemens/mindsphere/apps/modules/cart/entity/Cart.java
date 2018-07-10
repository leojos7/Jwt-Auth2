package com.siemens.mindsphere.apps.modules.cart.entity;

import com.siemens.mindsphere.apps.entity.BaseEntity;
import com.siemens.mindsphere.apps.modules.login.entity.User;
import com.siemens.mindsphere.apps.modules.product.entity.Product;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity(name = "CART")
public class Cart extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "LOGIN_ID")
    private User loginId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "PRODUCT_ID")
    private Product productId;

    private Integer quantity;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLoginId(User loginId) {
        this.loginId = loginId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
