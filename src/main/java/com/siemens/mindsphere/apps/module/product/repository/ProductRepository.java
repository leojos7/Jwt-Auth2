package com.siemens.mindsphere.apps.module.product.repository;

import com.siemens.mindsphere.apps.module.product.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

    List<Product> findByCode(String code);

}
