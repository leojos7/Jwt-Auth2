package com.siemens.mindsphere.apps.modules.order.service.orderParams;

import com.siemens.mindsphere.apps.modules.order.entity.OrderParams;
import com.siemens.mindsphere.apps.modules.order.repository.OrderParamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class OrderParamsServiceImpl implements OrderParamsService {

    @Autowired
    private OrderParamsRepository orderParamsRepository;

    @Override
    public OrderParams addOrderParams(OrderParams orderParams) {
        return orderParamsRepository.save(orderParams);
    }

    @Override
    public void deleteOrderParams(Integer orderParamsId) {
        Optional<OrderParams> orderParamsOptional = orderParamsRepository.findById(orderParamsId);
        if (orderParamsOptional.isPresent()) {
            orderParamsRepository.delete(orderParamsOptional.get());
        }
    }

    @Override
    public OrderParams updateOrderParams(OrderParams orderParams) {
        Optional<OrderParams> orderParamsOptional = orderParamsRepository.findById(orderParams.getId());
        OrderParams existingOrderParams = null;
        OrderParams newOrderParams = null;
        if (orderParamsOptional.isPresent()) {
            existingOrderParams = orderParamsOptional.get();
            existingOrderParams.setName(orderParams.getName());
            existingOrderParams.setDescription(orderParams.getDescription());
            existingOrderParams.setModifiedDate(new Date());
            newOrderParams = orderParamsRepository.save(existingOrderParams);
        } else {
            newOrderParams = orderParamsRepository.save(orderParams);
        }
        return newOrderParams;
    }

    @Override
    public OrderParams getOrderParams(Integer orderParamsId) {
        OrderParams orderParams = null;
        if(orderParamsRepository.findById(orderParamsId).isPresent()) {
            orderParams = orderParamsRepository.findById(orderParamsId).get();
        }
        return orderParams;
    }

    @Override
    public Page<OrderParams> getAllOrderParams(Pageable pageable) {
        return orderParamsRepository.findAll(pageable);
    }

}
