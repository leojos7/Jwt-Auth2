package com.siemens.mindsphere.apps.modules.order.service.order;

import com.siemens.mindsphere.apps.common.exception.ResourceNotFoundException;
import com.siemens.mindsphere.apps.modules.order.entity.Order;
import com.siemens.mindsphere.apps.modules.order.entity.OrderParamMapping;
import com.siemens.mindsphere.apps.modules.order.entity.OrderProductMapping;
import com.siemens.mindsphere.apps.modules.order.repository.OrderRepository;
import com.siemens.mindsphere.apps.modules.order.service.orderParams.OrderParamsService;
import com.siemens.mindsphere.apps.modules.product.service.product.ProductService;
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

    @Autowired
    private ProductService productService;

    @Override
    public Order addOrder(Order order) throws ResourceNotFoundException {

        for(OrderProductMapping orderProductMapping :order.getOrderProductMappings()) {
            orderProductMapping.setProduct(productService.getProduct(orderProductMapping.getProduct().getProductId()));
        }
        for(OrderParamMapping orderParamMapping : order.getOrderParamMappings()) {
            orderParamMapping.setOrderParam(orderParamsService.getOrderParams(orderParamMapping.getOrderParam().getOrderParamId()));
        }

        Order orderCreated = orderRepository.save(order);
        return getOrder(orderCreated.getOrderDetailId());
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
        Optional<Order> orderOptional = orderRepository.findById(order.getOrderDetailId());
        Order existingOrder = null;
        Order newOrder = null;
        if (orderOptional.isPresent()) {
            existingOrder = orderOptional.get();
            existingOrder.setOrderStatusId(order.getOrderStatusId());
            existingOrder.setUpdatedBy(order.getUpdatedBy());
            existingOrder.setModifiedDate(new Date());
            order.getOrderParamMappings().stream()
                    .forEach(locationParams -> orderParamsService.updateOrderParams(locationParams.getOrderParam()));
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
