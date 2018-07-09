package com.siemens.mindsphere.apps.module.order.controller;

import com.siemens.mindsphere.apps.module.location.service.location.LocationService;
import com.siemens.mindsphere.apps.module.login.exception.NoUserFoundException;
import com.siemens.mindsphere.apps.module.login.service.UserService;
import com.siemens.mindsphere.apps.module.order.dto.OrderDto;
import com.siemens.mindsphere.apps.module.order.entity.Order;
import com.siemens.mindsphere.apps.module.order.service.order.OrderService;
import com.siemens.mindsphere.apps.module.order.service.orderStatus.OrderStatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure/user/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LocationService locationService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderStatusService orderStatusService;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public OrderDto addOrder(@RequestBody OrderDto orderDto) throws NoUserFoundException {
        Order order = convertToEntity(orderDto);
        Order orderCreated = null;
        if (order != null) {
            orderCreated = orderService.addOrder(order);
        }
        return convertToDto(orderCreated);
    }

    @RequestMapping(value = "/delete/{orderId}", method = RequestMethod.GET)
    public void deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) throws NoUserFoundException {
        Order order = convertToEntity(orderDto);
        Order orderUpdated = null;
        if (order != null) {
            orderUpdated = orderService.updateOrder(order);
        }
        return convertToDto(orderUpdated);
    }
    @RequestMapping(value = "/get/{orderId}", method = RequestMethod.GET)
    public OrderDto getOrder(@PathVariable int orderId) {
        return convertToDto(orderService.getOrder(orderId));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Page<OrderDto> getAllOrders(Pageable pageable) {
        return convertToDtos(orderService.getAllOrders(pageable));
    }

    private Page<OrderDto> convertToDtos(Page<Order> orders) {
         return orders.map(orderToMap -> convertToDto(orderToMap));
    }

    private OrderDto convertToDto(Order order) {
        OrderDto orderDto = null;
        if (order != null) {
            orderDto = modelMapper.map(order, OrderDto.class);
            orderDto.setLoginId(order.getLoginId().getUsername());
            orderDto.setLocationId(order.getLocationId().getId());
            orderDto.setOrderStatusId(order.getOrderStatusId().getId());
            orderDto.setUpdatedBy(order.getUpdatedBy().getUsername());
        }
        return orderDto;
    }

    private Order convertToEntity(OrderDto orderDto) throws NoUserFoundException {
        Order order = null;
        if (orderDto != null) {
            order = modelMapper.map(orderDto, Order.class);
            order.setLoginId(userService.getUser(orderDto.getLoginId()));
            order.setLocationId(locationService.getLocation(orderDto.getLocationId()));
            order.setOrderStatusId(orderStatusService.getOrderStatus(orderDto.getOrderStatusId()));
            order.setUpdatedBy(userService.getUser(orderDto.getUpdatedBy()));
        } else {

        }
        return order;
    }


}
