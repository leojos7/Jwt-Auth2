package com.siemens.mindsphere.apps.module.order.service.orderStatus;

import com.siemens.mindsphere.apps.module.order.entity.OrderStatus;
import com.siemens.mindsphere.apps.module.product.entity.ProductParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface OrderStatusService {

    OrderStatus addOrderStatus(OrderStatus orderStatus);

    OrderStatus updateOrderStatus(OrderStatus orderStatus);

    void deleteOrderStatus(Integer orderStatusId);

    OrderStatus getOrderStatus(Integer orderStatusId);

    Page<OrderStatus> getAllOrderStatus(Pageable pageable);


}
