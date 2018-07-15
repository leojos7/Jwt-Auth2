package com.siemens.mindsphere.apps.modules.order.service.orderParams;

import com.siemens.mindsphere.apps.modules.order.entity.OrderParam;
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
    public OrderParam addOrderParams(OrderParam orderParam) {
        return orderParamsRepository.save(orderParam);
    }

    @Override
    public void deleteOrderParams(Integer orderParamsId) {
        Optional<OrderParam> orderParamsOptional = orderParamsRepository.findById(orderParamsId);
        if (orderParamsOptional.isPresent()) {
            orderParamsRepository.delete(orderParamsOptional.get());
        }
    }

    @Override
    public OrderParam updateOrderParams(OrderParam orderParam) {
        Optional<OrderParam> orderParamsOptional = orderParamsRepository.findById(orderParam.getOrderParamId());
        OrderParam existingOrderParam = null;
        OrderParam newOrderParam = null;
        if (orderParamsOptional.isPresent()) {
            existingOrderParam = orderParamsOptional.get();
            existingOrderParam.setName(orderParam.getName());
            existingOrderParam.setDescription(orderParam.getDescription());
            existingOrderParam.setModifiedDate(new Date());
            newOrderParam = orderParamsRepository.save(existingOrderParam);
        } else {
            newOrderParam = orderParamsRepository.save(orderParam);
        }
        return newOrderParam;
    }

    @Override
    public OrderParam getOrderParams(Integer orderParamsId) {
        OrderParam orderParam = null;
        if(orderParamsRepository.findById(orderParamsId).isPresent()) {
            orderParam = orderParamsRepository.findById(orderParamsId).get();
        }
        return orderParam;
    }

    @Override
    public Page<OrderParam> getAllOrderParams(Pageable pageable) {
        return orderParamsRepository.findAll(pageable);
    }

}
