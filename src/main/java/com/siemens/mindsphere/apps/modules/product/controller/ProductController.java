package com.siemens.mindsphere.apps.modules.product.controller;

import com.siemens.mindsphere.apps.modules.login.exception.UserNotFoundException;
import com.siemens.mindsphere.apps.modules.login.user.service.UserService;
import com.siemens.mindsphere.apps.modules.login.utils.CommonUtils;
import com.siemens.mindsphere.apps.modules.product.dto.ProductDto;
import com.siemens.mindsphere.apps.modules.product.entity.Product;
import com.siemens.mindsphere.apps.modules.product.exception.AlreadyExistingProductException;
import com.siemens.mindsphere.apps.modules.product.service.product.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import static com.siemens.mindsphere.apps.modules.login.utils.Constants.SUCCESSFULLY_SAVED;

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
    public String addProduct(@RequestHeader("Authorization") String authorization,
                                 @RequestBody ProductDto productDto)
            throws AlreadyExistingProductException, UserNotFoundException {
        String username = CommonUtils.getUsernameFromAccessToken(authorization);
        Product product = convertToEntity(productDto, username);
        Integer productId = null;
        if (product != null) {
            productId = productService.addProduct(product).getProductId();
        }
        return SUCCESSFULLY_SAVED + "product with id "+productId;
    }

    @RequestMapping(value = "/delete/{productId}", method = RequestMethod.GET)
    public void deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ProductDto updateProduct(@RequestHeader("Authorization") String authorization,
                                    @RequestBody ProductDto productDto) throws UserNotFoundException {
        String username = CommonUtils.getUsernameFromAccessToken(authorization);
        Product product = convertToEntity(productDto, username);
        Product productUpdated = null;
        if (product != null) {
            productUpdated = productService.updateProduct(product);
        }
        return convertToDto(productUpdated);
    }

    @RequestMapping(value = "/get/{productId}", method = RequestMethod.GET)
    public ProductDto getProduct(@PathVariable int productId) {
        return convertToDto(productService.getProduct(productId));
    }

    @RequestMapping(value = "/getAllProducts", method = RequestMethod.GET, produces = "application/json")
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        return convertToDtos(productService.getAllProducts(pageable));
    }

    @RequestMapping(value = "/decode/{code}", method = RequestMethod.GET)
    public ProductDto zDecoder(@PathVariable String code) {
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

    private Product convertToEntity(ProductDto productDto, String username) throws UserNotFoundException {
        Product product = null;

        if (productDto != null) {
            product = modelMapper.map(productDto, Product.class);
            product.setAddedBy(userService.getUserByUsername(username));
        } else {

        }
        return product;
    }
}
