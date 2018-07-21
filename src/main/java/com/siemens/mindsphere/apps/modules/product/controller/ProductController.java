package com.siemens.mindsphere.apps.modules.product.controller;

import com.siemens.mindsphere.apps.common.exception.AlreadyExistingResourceException;
import com.siemens.mindsphere.apps.common.exception.ResourceNotFoundException;
import com.siemens.mindsphere.apps.modules.login.user.service.UserService;
import com.siemens.mindsphere.apps.common.utils.CommonUtils;
import com.siemens.mindsphere.apps.modules.product.dto.ProductDto;
import com.siemens.mindsphere.apps.modules.product.entity.Product;
import com.siemens.mindsphere.apps.modules.product.service.product.ProductService;
import com.siemens.mindsphere.apps.modules.product.validator.ProductValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.siemens.mindsphere.apps.common.utils.Constants.SUCCESSFULLY_SAVED;

@CrossOrigin
@RestController
@RequestMapping("/secure/user/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductValidator productValidator;

    @InitBinder("productDto")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(productValidator);
    }

    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            consumes = "application/json")
    public String addProduct(@RequestHeader("Authorization") String authorization,
                             @Valid @RequestBody ProductDto productDto)
            throws AlreadyExistingResourceException, ResourceNotFoundException {
        String username = CommonUtils.getUsernameFromAccessToken(authorization);
        Product product = convertToEntity(productDto, username);
        Integer productId = null;
        if (product != null) {
            productId = productService.addProduct(product).getProductId();
        }
        return SUCCESSFULLY_SAVED + "product with id "+productId;
    }

    @RequestMapping(value = "/delete/{productId}",
            method = RequestMethod.GET)
    public void deleteProduct(@PathVariable int productId) throws ResourceNotFoundException {
        productService.deleteProduct(productId);
    }

    @RequestMapping(value = "/update",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    public ProductDto updateProduct(@RequestHeader("Authorization") String authorization,
                                    @RequestBody ProductDto productDto) throws ResourceNotFoundException {
        String username = CommonUtils.getUsernameFromAccessToken(authorization);
        Product product = convertToEntity(productDto, username);
        Product productUpdated = null;
        if (product != null) {
            productUpdated = productService.updateProduct(product);
        }
        return convertToDto(productUpdated);
    }

    @RequestMapping(value = "/get/{productId}",
            method = RequestMethod.GET,
            produces = "application/json")
    public ProductDto getProduct(@PathVariable int productId) throws ResourceNotFoundException {
        return convertToDto(productService.getProduct(productId));
    }

    @RequestMapping(value = "/getAllProducts",
            method = RequestMethod.GET,
            produces = "application/json")
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        return convertToDtos(productService.getAllProducts(pageable));
    }

    @RequestMapping(value = "/decode/{code}",
            method = RequestMethod.GET,
            produces = "application/json")
    public ProductDto zDecoder(@PathVariable String code) throws ResourceNotFoundException {
        return convertToDto(productService.zDecoder(code));
    }

    public Page<ProductDto> convertToDtos(Page<Product> product) {
        return product.map(productToMap -> convertToDto(productToMap));
    }

    private ProductDto convertToDto(Product product) {
        ProductDto productDto = null;
        if (product != null) {
            productDto = modelMapper.map(product, ProductDto.class);
        }
        return productDto;
    }

    private Product convertToEntity(ProductDto productDto, String username) throws ResourceNotFoundException {
        Product product = null;

        if (productDto != null) {
            product = modelMapper.map(productDto, Product.class);
            product.setAddedBy(userService.getUserByUsername(username));
        } else {

        }
        return product;
    }

}
