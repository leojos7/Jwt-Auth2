package com.siemens.mindsphere.apps.modules.order.controller;

import com.siemens.mindsphere.apps.modules.location.service.location.LocationService;
import com.siemens.mindsphere.apps.modules.login.exception.NoUserFoundException;
import com.siemens.mindsphere.apps.modules.login.user.service.UserService;
import com.siemens.mindsphere.apps.modules.order.dto.OrderDto;
import com.siemens.mindsphere.apps.modules.order.dto.ProductDetailsDto;
import com.siemens.mindsphere.apps.modules.order.entity.Order;
import com.siemens.mindsphere.apps.modules.order.entity.OrderProductMapping;
import com.siemens.mindsphere.apps.modules.order.service.order.OrderService;
import com.siemens.mindsphere.apps.modules.order.service.orderStatus.OrderStatusService;
import com.siemens.mindsphere.apps.modules.product.dto.ProductDto;
import com.siemens.mindsphere.apps.modules.product.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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


    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
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
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        OrderDto orderDto = null;
        if (order != null) {
            orderDto = modelMapper.map(order, OrderDto.class);
            orderDto.setLocationId(order.getLocationId().getLocationId());
            orderDto.setOrderStatusId(order.getOrderStatusId().getOrderStatusId());
            orderDto.setProducts(order.getOrderProductMappings().stream()
                    .map(orderProductMapping -> new ProductDetailsDto(
                            modelMapper.map(orderProductMapping.getProduct(), ProductDto.class),
                            orderProductMapping.getQuantity()))
                    .collect(Collectors.toSet()));
        }
        return orderDto;
    }

    private Order convertToEntity(OrderDto orderDto) throws NoUserFoundException {
        Order order = null;
        if (orderDto != null) {
            order = modelMapper.map(orderDto, Order.class);
            order.setLoginId(userService.getUserById(orderDto.getLoginId().getId()));
            order.setLocationId(locationService.getLocation(orderDto.getLocationId()));
            order.setOrderStatusId(orderStatusService.getOrderStatus(orderDto.getOrderStatusId()));
            order.setUpdatedBy(userService.getUserById(orderDto.getUpdatedBy().getId()));
            Set<OrderProductMapping> orderProductMappings = new HashSet();
            Order finalOrder = order;
            orderDto.getProducts().stream()
                    .forEach(productDtoIntegerEntry -> {
                        OrderProductMapping orderProductMapping = new OrderProductMapping();
                        Product product = modelMapper.map(productDtoIntegerEntry, Product.class);
                        orderProductMapping.setOrder(finalOrder);
                        orderProductMapping.setProduct(product);
                        orderProductMapping.setQuantity(productDtoIntegerEntry.getQuantity());
                        orderProductMappings.add(orderProductMapping);
            });
            order.setOrderProductMappings(orderProductMappings);
        } else {

        }
        return order;
    }


}
