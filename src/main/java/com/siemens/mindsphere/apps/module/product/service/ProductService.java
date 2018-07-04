package com.siemens.mindsphere.apps.module.product.service;

import com.siemens.mindsphere.apps.module.product.entity.Product;
import com.siemens.mindsphere.apps.module.product.entity.ProductParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    public Product addProduct(Product product);

    public void deleteProduct(Integer productId);

    public Product updateProduct(Product product);

    public Page<Product> getAllProducts(Pageable pageable);

    public Product getProduct(Integer productId);

}
