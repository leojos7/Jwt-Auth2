package com.siemens.mindsphere.apps.module.cart.service;

import com.siemens.mindsphere.apps.module.cart.entity.Cart;
import com.siemens.mindsphere.apps.module.cart.repository.CartRepository;
import com.siemens.mindsphere.apps.module.login.exception.NoUserFoundException;
import com.siemens.mindsphere.apps.module.login.service.UserService;
import com.siemens.mindsphere.apps.module.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Override
    public Cart addToCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void deleteFromCart(Integer cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            cartRepository.delete(cartOptional.get());
        }
    }

    @Override
    public Cart updateCart(Cart cart) {
        Optional<Cart> cartOptional = cartRepository.findById(cart.getId());
        Cart existingCart = null;
        Cart newCart = null;
        if (cartOptional.isPresent()) {
            existingCart = cartOptional.get();
            existingCart.setQuantity(cart.getQuantity());
            existingCart.setModifiedDate(new Date());
            newCart = cartRepository.save(existingCart);
        } else {
            newCart = cartRepository.save(cart);
        }
        return newCart;
    }

    @Override
    public Page<Cart> getCart(String userId, Pageable pageable) throws NoUserFoundException {
        return cartRepository.findByLoginId(userService.getUser(userId), pageable);
    }

}
