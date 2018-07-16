package com.siemens.mindsphere.apps.modules.cart.service;

import com.siemens.mindsphere.apps.modules.cart.entity.Cart;
import com.siemens.mindsphere.apps.modules.login.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartService {

    Cart addToCart(Cart cart);

    void deleteFromCart(Integer cartId);

    Cart updateCart(Cart cart);

    Page<Cart> getCart(Integer userId, Pageable pageable) throws UserNotFoundException;
}
