package com.siemens.mindsphere.apps.modules.order.controller;

import com.siemens.mindsphere.apps.modules.order.dto.OrderStatusDto;
import com.siemens.mindsphere.apps.modules.order.entity.OrderStatus;
import com.siemens.mindsphere.apps.modules.order.service.orderStatus.OrderStatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure/user/orderStatus")
public class OrderStatusController {

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public OrderStatusDto getOrderStatus(@RequestBody OrderStatusDto orderStatusDto) {
        OrderStatus orderStatus = convertToEntity(orderStatusDto);
        OrderStatus orderStatusCreated = null;
        if (orderStatus != null) {
            orderStatusCreated = orderStatusService.addOrderStatus(orderStatus);
        }
        return convertToDto(orderStatusCreated);
    }

    @RequestMapping(value = "/get/{orderStatusId}", method = RequestMethod.GET)
    public OrderStatusDto getOrderStatus(@PathVariable int orderStatusId) {
        return convertToDto(orderStatusService.getOrderStatus(orderStatusId));
    }

    @RequestMapping(value = "/delete/{orderStatusId}", method = RequestMethod.GET)
    public void deleteOrderStatus(@PathVariable int orderStatusId) {
        orderStatusService.deleteOrderStatus(orderStatusId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public OrderStatusDto updateOrderStatus(@RequestBody OrderStatusDto orderStatusDto) {
        OrderStatus orderStatus = convertToEntity(orderStatusDto);
        OrderStatus orderStatusUpdated = null;
        if (orderStatus != null) {
            orderStatusUpdated = orderStatusService.updateOrderStatus(orderStatus);
        }
        return convertToDto(orderStatusUpdated);
    }

    @RequestMapping(value = "/getAllOrderStatus", method = RequestMethod.GET, produces = "application/json")
    public Page<OrderStatusDto> getAllOrderStatus(Pageable pageable) {
        return convertToDtos(orderStatusService.getAllOrderStatus(pageable));
    }

    private Page<OrderStatusDto> convertToDtos(Page<OrderStatus> orderStatus) {
        return orderStatus.map(orderStatusToBeConverted -> convertToDto(orderStatusToBeConverted));
    }

    private OrderStatusDto convertToDto(OrderStatus orderStatus) {
        OrderStatusDto orderStatusDto = null;
        if (orderStatus != null) {
            orderStatusDto = modelMapper.map(orderStatus, OrderStatusDto.class);
        }
        return orderStatusDto;
    }

    private OrderStatus convertToEntity(OrderStatusDto orderStatusDto) {
        OrderStatus orderStatus = null;
        if (orderStatusDto != null) {
            orderStatus = modelMapper.map(orderStatusDto, OrderStatus.class);
        }
        return orderStatus;
    }


}
