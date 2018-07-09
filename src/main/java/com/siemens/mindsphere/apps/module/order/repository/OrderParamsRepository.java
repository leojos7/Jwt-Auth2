package com.siemens.mindsphere.apps.module.order.repository;

import com.siemens.mindsphere.apps.module.order.entity.OrderParams;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderParamsRepository extends PagingAndSortingRepository<OrderParams, Integer> {
}
