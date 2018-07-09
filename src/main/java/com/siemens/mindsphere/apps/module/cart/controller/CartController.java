package com.siemens.mindsphere.apps.module.cart.controller;

import com.siemens.mindsphere.apps.module.cart.dto.CartDto;
import com.siemens.mindsphere.apps.module.cart.entity.Cart;
import com.siemens.mindsphere.apps.module.cart.service.CartService;
import com.siemens.mindsphere.apps.module.login.exception.NoUserFoundException;
import com.siemens.mindsphere.apps.module.login.service.UserService;
import com.siemens.mindsphere.apps.module.product.service.product.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure/user/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CartDto addToCart(@RequestBody CartDto cartDto)
            throws NoUserFoundException {
        Cart cart = convertToEntity(cartDto);
        Cart cartCreated = null;
        if (cart != null) {
            cartCreated = cartService.addToCart(cart);
        }
        return convertToDto(cartCreated);
    }

    @RequestMapping(value = "/delete/{cartId}", method = RequestMethod.GET)
    public void deleteFromCart(@PathVariable int cartId) {
        cartService.deleteFromCart(cartId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CartDto updateCart(@RequestBody CartDto cartDto) throws NoUserFoundException {
        Cart cart = convertToEntity(cartDto);
        Cart cartUpdated = null;
        if (cart != null) {
            cartUpdated = cartService.updateCart(cart);
        }
        return convertToDto(cartUpdated);
    }

    @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
    public Page<CartDto> getCart(@PathVariable Integer userId, Pageable pageable) throws NoUserFoundException {
        return convertToDtos(cartService.getCart(userId, pageable));
    }

    private Page<CartDto> convertToDtos(Page<Cart> carts) {
        return carts.map(cartToMap -> convertToDto(cartToMap));
    }

    private CartDto convertToDto(Cart cart) {
        CartDto cartDto = null;
        if (cart != null) {
            cartDto = modelMapper.map(cart, CartDto.class);
            cartDto.setLoginId(cart.getLoginId().getId());
            cartDto.setProductId(cart.getProductId().getId());
        }
        return cartDto;
    }

    private Cart convertToEntity(CartDto cartDto) throws NoUserFoundException {
        Cart cart = null;
        if (cartDto != null) {
            cart = modelMapper.map(cartDto, Cart.class);
            cart.setLoginId(userService.getUserById(cartDto.getLoginId()));
            cart.setProductId(productService.getProduct(cartDto.getProductId()));
        } else {

        }
        return cart;
    }

}
