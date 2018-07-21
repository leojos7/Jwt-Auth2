package com.siemens.mindsphere.apps.modules.product.service.product;

import com.siemens.mindsphere.apps.common.exception.AlreadyExistingResourceException;
import com.siemens.mindsphere.apps.common.exception.ResourceNotFoundException;
import com.siemens.mindsphere.apps.modules.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Product addProduct(Product product) throws AlreadyExistingResourceException, ResourceNotFoundException;

    void deleteProduct(Integer productId) throws ResourceNotFoundException;

    Product updateProduct(Product product) throws ResourceNotFoundException;

    Page<Product> getAllProducts(Pageable pageable);

    Product getProduct(Integer productId) throws ResourceNotFoundException;

    Product zDecoder(String code) throws ResourceNotFoundException;

}
