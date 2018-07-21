package com.siemens.mindsphere.apps.modules.cart.controller;

import com.siemens.mindsphere.apps.modules.cart.dto.CartDto;
import com.siemens.mindsphere.apps.modules.cart.entity.Cart;
import com.siemens.mindsphere.apps.modules.cart.service.CartService;
import com.siemens.mindsphere.apps.common.exception.ResourceNotFoundException;
import com.siemens.mindsphere.apps.modules.login.user.dto.UserDto;
import com.siemens.mindsphere.apps.modules.login.user.service.UserService;
import com.siemens.mindsphere.apps.modules.product.dto.ProductDto;
import com.siemens.mindsphere.apps.modules.product.service.product.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
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

    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            consumes = "application/json")
    public CartDto addToCart(@RequestBody CartDto cartDto)
            throws ResourceNotFoundException {
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

    @RequestMapping(value = "/update",
            method = RequestMethod.POST,
            produces = "application/json")
    public CartDto updateCart(@RequestBody CartDto cartDto) throws ResourceNotFoundException {
        Cart cart = convertToEntity(cartDto);
        Cart cartUpdated = null;
        if (cart != null) {
            cartUpdated = cartService.updateCart(cart);
        }
        return convertToDto(cartUpdated);
    }

    @RequestMapping(value = "/get/{userId}",
            method = RequestMethod.GET,
            produces = "application/json")
    public Page<CartDto> getCart(@PathVariable Integer userId, Pageable pageable) throws ResourceNotFoundException {
        return convertToDtos(cartService.getCart(userId, pageable));
    }

    private Page<CartDto> convertToDtos(Page<Cart> carts) {
        return carts.map(cartToMap -> convertToDto(cartToMap));
    }

    private CartDto convertToDto(Cart cart) {
        CartDto cartDto = null;
        if (cart != null) {
            cartDto = modelMapper.map(cart, CartDto.class);
            cartDto.setUser(modelMapper.map(cart.getLoginId(), UserDto.class));
            cartDto.setProduct(modelMapper.map(cart.getProductId(),ProductDto.class));
        }
        return cartDto;
    }

    private Cart convertToEntity(CartDto cartDto) throws ResourceNotFoundException {
        Cart cart = null;
        if (cartDto != null) {
            cart = modelMapper.map(cartDto, Cart.class);
            cart.setLoginId(userService.getUserById(cartDto.getUser().getUserId()));
            cart.setProductId(productService.getProduct(cartDto.getProduct().getProductId()));
        } else {

        }
        return cart;
    }

}
