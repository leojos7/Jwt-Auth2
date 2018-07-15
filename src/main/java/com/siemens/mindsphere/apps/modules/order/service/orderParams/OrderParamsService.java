package com.siemens.mindsphere.apps.modules.order.service.orderParams;

import com.siemens.mindsphere.apps.modules.order.entity.OrderParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderParamsService {

    OrderParam addOrderParams(OrderParam orderParam);

    void deleteOrderParams(Integer orderParamsId);

    OrderParam updateOrderParams(OrderParam orderParam);

    OrderParam getOrderParams(Integer orderParamsId);

    Page<OrderParam> getAllOrderParams(Pageable pageable);
}
