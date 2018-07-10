package com.siemens.mindsphere.apps.modules.order.service.order;

import com.siemens.mindsphere.apps.modules.order.entity.Order;
import com.siemens.mindsphere.apps.modules.order.repository.OrderRepository;
import com.siemens.mindsphere.apps.modules.order.service.orderParams.OrderParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderParamsService orderParamsService;

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            orderRepository.delete(orderOptional.get());
        }
    }

    @Override
    public Order updateOrder(Order order) {
        Optional<Order> orderOptional = orderRepository.findById(order.getId());
        Order existingOrder = null;
        Order newOrder = null;
        if (orderOptional.isPresent()) {
            existingOrder = orderOptional.get();
            existingOrder.setOrderStatusId(order.getOrderStatusId());
            existingOrder.setUpdatedBy(order.getUpdatedBy());
            existingOrder.setModifiedDate(new Date());
            order.getOrderParams().stream()
                    .forEach(locationParams -> orderParamsService.updateOrderParams(locationParams));
            newOrder = orderRepository.save(existingOrder);
        } else {
            newOrder = orderRepository.save(order);
        }
        return newOrder;
    }

    @Override
    public Order getOrder(Integer orderId) {
        Order order = null;
        if(orderRepository.findById(orderId).isPresent()) {
            order = orderRepository.findById(orderId).get();
        }
        return order;
    }

    @Override
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

}
