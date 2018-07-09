package com.siemens.mindsphere.apps.module.order.repository;

import com.siemens.mindsphere.apps.module.order.entity.Order;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {
}
