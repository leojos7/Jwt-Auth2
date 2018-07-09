package com.siemens.mindsphere.apps.module.cart.service;

import com.siemens.mindsphere.apps.module.cart.entity.Cart;
import com.siemens.mindsphere.apps.module.login.exception.NoUserFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartService {

    Cart addToCart(Cart cart);

    void deleteFromCart(Integer cartId);

    Cart updateCart(Cart cart);

    Page<Cart> getCart(Integer userId, Pageable pageable) throws NoUserFoundException;
}
