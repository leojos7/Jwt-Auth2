package com.siemens.mindsphere.apps.modules.order.service.orderStatus;

import com.siemens.mindsphere.apps.modules.order.entity.OrderStatus;
import com.siemens.mindsphere.apps.modules.order.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class OrderStatusServiceImpl implements OrderStatusService {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Override
    public OrderStatus addOrderStatus(OrderStatus orderStatus) {
        return orderStatusRepository.save(orderStatus);
    }

    @Override
    public OrderStatus updateOrderStatus(OrderStatus orderStatus) {
        Optional<OrderStatus> orderStatusOptional = orderStatusRepository.findById(orderStatus.getOrderStatusId());
        OrderStatus existingOrderStatus = null;
        OrderStatus newOrderStatus = null;
        if (orderStatusOptional.isPresent()) {
            existingOrderStatus = orderStatusOptional.get();
            existingOrderStatus.setName(orderStatus.getName());
            existingOrderStatus.setDescription(orderStatus.getDescription());
            existingOrderStatus.setModifiedDate(new Date());
            newOrderStatus = orderStatusRepository.save(existingOrderStatus);
        } else {
            newOrderStatus = orderStatusRepository.save(orderStatus);
        }
        return newOrderStatus;
    }

    @Override
    public OrderStatus getOrderStatus(Integer orderStatusId) {
        Optional<OrderStatus> existingOrderStatusOptional = orderStatusRepository.findById(orderStatusId);
        OrderStatus existingOrderStatus = null;
        if (existingOrderStatusOptional.isPresent()) {
            existingOrderStatus = existingOrderStatusOptional.get();
        } else {
            // TODO throw custom exception
        }
        return existingOrderStatus;
    }

    @Override
    public void deleteOrderStatus(Integer orderStatusId) {
        if (orderStatusRepository.findById(orderStatusId) != null) {
            orderStatusRepository.deleteById(orderStatusId);
        } else {
            // TODO throw custom exception
        }
    }

    @Override
    public Page<OrderStatus> getAllOrderStatus(Pageable pageable) {
        return orderStatusRepository.findAll(pageable);
    }
}
