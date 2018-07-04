package com.siemens.mindsphere.apps.module.product.repository;

import com.siemens.mindsphere.apps.module.product.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

}
