package com.siemens.mindsphere.apps.module.order.service.order;

import com.siemens.mindsphere.apps.module.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Order addOrder(Order order);

    void deleteOrder(Integer orderId);

    Order updateOrder(Order order);

    Order getOrder(Integer orderId);

    Page<Order> getAllOrders(Pageable pageable);
}
