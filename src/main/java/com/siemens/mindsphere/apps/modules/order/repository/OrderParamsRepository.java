package com.siemens.mindsphere.apps.modules.order.repository;

import com.siemens.mindsphere.apps.modules.order.entity.OrderParam;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderParamsRepository extends PagingAndSortingRepository<OrderParam, Integer> {
}
