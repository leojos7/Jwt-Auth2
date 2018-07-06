package com.siemens.mindsphere.apps.module.product.controller;

import com.siemens.mindsphere.apps.module.login.exception.NoUserFoundException;
import com.siemens.mindsphere.apps.module.login.service.UserService;
import com.siemens.mindsphere.apps.module.product.dto.ProductDto;
import com.siemens.mindsphere.apps.module.product.entity.Product;
import com.siemens.mindsphere.apps.module.product.exception.AlreadyExistingProductException;
import com.siemens.mindsphere.apps.module.product.service.product.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import org.modelmapper.ModelMapper;

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
    public ProductDto addProduct(@RequestBody ProductDto productDto)
            throws NoUserFoundException, AlreadyExistingProductException {
        Product product = convertToEntity(productDto);
        Product productCreated = null;
        if(product != null) {
            productCreated = productService.addProduct(product);
        }
        return convertToDto(productCreated);
    }

    @RequestMapping(value = "/delete/{productId}", method = RequestMethod.GET)
    public void deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ProductDto updateProduct(@RequestBody ProductDto productDto) throws NoUserFoundException {
        Product product = convertToEntity(productDto);
        Product productUpdated = null;
        if(product != null) {
            productUpdated = productService.updateProduct(product);
        }
        return convertToDto(productUpdated);
    }

    @RequestMapping(value = "/get/{productId}", method = RequestMethod.GET)
    public ProductDto getProduct(@PathVariable int productId) throws NoUserFoundException {
        return convertToDto(productService.getProduct(productId));
    }

    @RequestMapping(value = "/getAllProducts", method = RequestMethod.GET, produces = "application/json")
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        return convertToDtos(productService.getAllProducts(pageable));
    }

    @RequestMapping(value = "/decode/{code}", method = RequestMethod.GET)
    public ProductDto zDecoder(@PathVariable String code) throws NoUserFoundException {
        return convertToDto(productService.zDecoder(code));
    }

    private Page<ProductDto> convertToDtos(Page<Product> product) {
        return product.map(productToMap -> {
            ProductDto productDto = null;
            try {
                productDto = convertToDto(productToMap);
            } catch (NoUserFoundException e) {
                e.getMessage();
            }
            return productDto;
        });
    }

    private ProductDto convertToDto(Product product) throws NoUserFoundException {
        ProductDto productDto = null;
        if(product != null) {
            productDto = modelMapper.map(product, ProductDto.class);
            productDto.setAddedBy(userService.getUser(product.getAddedBy().getUsername()).getUsername());
        }
        return productDto;
    }

    private Product convertToEntity(ProductDto productDto) throws NoUserFoundException {
        Product product = null;
        if(productDto != null) {
            product = modelMapper.map(productDto, Product.class);
            product.setAddedBy(userService.getUser(productDto.getAddedBy()));
        } else {

        }
        return product;
    }
}
