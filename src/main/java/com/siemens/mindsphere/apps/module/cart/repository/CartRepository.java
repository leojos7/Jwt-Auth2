package com.siemens.mindsphere.apps.module.cart.repository;

import com.siemens.mindsphere.apps.module.cart.entity.Cart;
import com.siemens.mindsphere.apps.module.login.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface CartRepository extends PagingAndSortingRepository<Cart, Integer> {

    Page<Cart> findByLoginId(@Param("loginId") User loginId, Pageable pageRequest);
}
