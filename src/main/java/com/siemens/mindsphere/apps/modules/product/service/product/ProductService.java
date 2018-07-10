package com.siemens.mindsphere.apps.modules.product.service.product;

import com.siemens.mindsphere.apps.modules.product.entity.Product;
import com.siemens.mindsphere.apps.modules.product.exception.AlreadyExistingProductException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Product addProduct(Product product) throws AlreadyExistingProductException;

    void deleteProduct(Integer productId);

    Product updateProduct(Product product);

    Page<Product> getAllProducts(Pageable pageable);

    Product getProduct(Integer productId);

    Product zDecoder(String code);

}
