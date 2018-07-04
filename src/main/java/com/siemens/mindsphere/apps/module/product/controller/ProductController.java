package com.siemens.mindsphere.apps.module.product.controller;

import com.siemens.mindsphere.apps.module.login.exception.NoUserFoundException;
import com.siemens.mindsphere.apps.module.login.service.UserService;
import com.siemens.mindsphere.apps.module.product.dto.ProductDto;
import com.siemens.mindsphere.apps.module.product.entity.Product;
import com.siemens.mindsphere.apps.module.product.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


@RestController
@RequestMapping("/secure/user/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto addProduct(@RequestBody ProductDto productDto) throws ParseException, NoUserFoundException {
        Product product = convertToEntity(productDto);
        Product productCreated = null;
        if(product != null) {
            productCreated = productService.addProduct(product);
        }
        return convertToDto(productCreated);
    }

    @RequestMapping(value = "/get/{productId}", method = RequestMethod.GET)
    public ProductDto getProduct(@PathVariable int productId) {
        return convertToDto(productService.getProduct(productId));
    }

    @RequestMapping(value = "/delete/{productId}", method = RequestMethod.GET)
    public void deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
    }

    @RequestMapping(value = "/getAllProducts", method = RequestMethod.GET, produces = "application/json")
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        return convertToDtos(productService.getAllProducts(pageable));
    }

    private Page<ProductDto> convertToDtos(Page<Product> product) {
        return product.map(product1 -> convertToDto(product1));
    }

    private ProductDto convertToDto(Product product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        productDto.setAddedBy(product.getAddedBy().getUsername());
        return productDto;
    }

    private Product convertToEntity(ProductDto productDto) throws ParseException, NoUserFoundException {
        Product product = modelMapper.map(productDto, Product.class);
        product.setAddedBy(userService.getUser(productDto.getAddedBy()));
        return product;
    }

}
