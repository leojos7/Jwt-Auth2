package com.siemens.mindsphere.apps.modules.order.controller;

import com.siemens.mindsphere.apps.modules.order.dto.OrderParamDto;
import com.siemens.mindsphere.apps.modules.order.entity.OrderParam;
import com.siemens.mindsphere.apps.modules.order.service.orderParams.OrderParamsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/secure/user/orderParams")
public class OrderParamsController {

    @Autowired
    private OrderParamsService orderParamsService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    public OrderParamDto addOrderParams(@RequestBody OrderParamDto orderParamDto) {
        OrderParam location = convertToEntity(orderParamDto);
        OrderParam orderParam = null;
        if (location != null) {
            orderParam = orderParamsService.addOrderParams(location);
        }
        return convertToDto(orderParam);
    }

    @RequestMapping(value = "/delete/{orderParamsId}",
            method = RequestMethod.GET)
    public void deleteOrderParams(@PathVariable int orderParamsId) {
        orderParamsService.deleteOrderParams(orderParamsId);
    }

    @RequestMapping(value = "/update",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    public OrderParamDto updateOrderParams(@RequestBody OrderParamDto orderParamDto) {
        OrderParam location = convertToEntity(orderParamDto);
        OrderParam locationUpdated = null;
        if (location != null) {
            locationUpdated = orderParamsService.updateOrderParams(location);
        }
        return convertToDto(locationUpdated);
    }
    @RequestMapping(value = "/get/{orderParamsId}",
            method = RequestMethod.GET,
            produces = "application/json")
    public OrderParamDto getOrderParams(@PathVariable int orderParamsId) {
        return convertToDto(orderParamsService.getOrderParams(orderParamsId));
    }

    @RequestMapping(value = "/get",
            method = RequestMethod.GET,
            produces = "application/json")
    public Page<OrderParamDto> getAllOrderParams(Pageable pageable) {
        return convertToDtos(orderParamsService.getAllOrderParams(pageable));
    }

    private Page<OrderParamDto> convertToDtos(Page<OrderParam> orderParams) {
        return orderParams.map(locationsToMap -> convertToDto(locationsToMap));
    }

    private OrderParamDto convertToDto(OrderParam orderParam) {
        OrderParamDto orderParamDto = null;
        if (orderParam != null) {
            orderParamDto = modelMapper.map(orderParam, OrderParamDto.class);
        }
        return orderParamDto;
    }

    private OrderParam convertToEntity(OrderParamDto orderParamDto) {
        OrderParam orderParam = null;
        if (orderParamDto != null) {
            orderParam = modelMapper.map(orderParamDto, OrderParam.class);
        } else {

        }
        return orderParam;
    }

}
