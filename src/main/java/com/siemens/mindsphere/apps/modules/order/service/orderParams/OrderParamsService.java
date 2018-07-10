package com.siemens.mindsphere.apps.modules.order.service.orderParams;

import com.siemens.mindsphere.apps.modules.order.entity.OrderParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderParamsService {

    OrderParams addOrderParams(OrderParams orderParams);

    void deleteOrderParams(Integer orderParamsId);

    OrderParams updateOrderParams(OrderParams orderParams);

    OrderParams getOrderParams(Integer orderParamsId);

    Page<OrderParams> getAllOrderParams(Pageable pageable);
}
