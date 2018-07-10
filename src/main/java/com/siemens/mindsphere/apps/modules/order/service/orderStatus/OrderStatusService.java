package com.siemens.mindsphere.apps.modules.order.service.orderStatus;

import com.siemens.mindsphere.apps.modules.order.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderStatusService {

    OrderStatus addOrderStatus(OrderStatus orderStatus);

    OrderStatus updateOrderStatus(OrderStatus orderStatus);

    void deleteOrderStatus(Integer orderStatusId);

    OrderStatus getOrderStatus(Integer orderStatusId);

    Page<OrderStatus> getAllOrderStatus(Pageable pageable);


}
