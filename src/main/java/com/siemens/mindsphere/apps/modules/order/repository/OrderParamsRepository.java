package com.siemens.mindsphere.apps.modules.order.repository;

import com.siemens.mindsphere.apps.modules.order.entity.OrderParams;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderParamsRepository extends PagingAndSortingRepository<OrderParams, Integer> {
}
