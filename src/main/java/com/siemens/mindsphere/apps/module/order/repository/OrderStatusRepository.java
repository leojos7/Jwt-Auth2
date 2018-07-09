package com.siemens.mindsphere.apps.module.order.repository;

import com.siemens.mindsphere.apps.module.order.entity.OrderStatus;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderStatusRepository extends PagingAndSortingRepository<OrderStatus, Integer> {
}
