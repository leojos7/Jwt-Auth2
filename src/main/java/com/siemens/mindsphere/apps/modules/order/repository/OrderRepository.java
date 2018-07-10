package com.siemens.mindsphere.apps.modules.order.repository;

import com.siemens.mindsphere.apps.modules.order.entity.Order;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {
}
