package com.siemens.mindsphere.apps.modules.order.repository;

import com.siemens.mindsphere.apps.modules.order.entity.OrderStatus;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderStatusRepository extends PagingAndSortingRepository<OrderStatus, Integer> {
}
