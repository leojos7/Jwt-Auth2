package com.siemens.mindsphere.apps.modules.order.controller;

import com.siemens.mindsphere.apps.modules.order.dto.OrderParamsDto;
import com.siemens.mindsphere.apps.modules.order.entity.OrderParams;
import com.siemens.mindsphere.apps.modules.order.service.orderParams.OrderParamsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure/user/orderParams")
public class OrderParamsController {

    @Autowired
    private OrderParamsService orderParamsService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public OrderParamsDto addLocation(@RequestBody OrderParamsDto orderParamsDto) {
        OrderParams location = convertToEntity(orderParamsDto);
        OrderParams orderParams = null;
        if (location != null) {
            orderParams = orderParamsService.addOrderParams(location);
        }
        return convertToDto(orderParams);
    }

    @RequestMapping(value = "/delete/{locationId}", method = RequestMethod.GET)
    public void deleteLocation(@PathVariable int locationId) {
        orderParamsService.deleteOrderParams(locationId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public OrderParamsDto updateLocation(@RequestBody OrderParamsDto orderParamsDto) {
        OrderParams location = convertToEntity(orderParamsDto);
        OrderParams locationUpdated = null;
        if (location != null) {
            locationUpdated = orderParamsService.updateOrderParams(location);
        }
        return convertToDto(locationUpdated);
    }
    @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
    public OrderParamsDto getLocation(@PathVariable int locationParamsId) {
        return convertToDto(orderParamsService.getOrderParams(locationParamsId));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Page<OrderParamsDto> getAllLocations(Pageable pageable) {
        return convertToDtos(orderParamsService.getAllOrderParams(pageable));
    }

    private Page<OrderParamsDto> convertToDtos(Page<OrderParams> orderParams) {
        return orderParams.map(locationsToMap -> convertToDto(locationsToMap));
    }

    private OrderParamsDto convertToDto(OrderParams orderParams) {
        OrderParamsDto orderParamsDto = null;
        if (orderParams != null) {
            orderParamsDto = modelMapper.map(orderParams, OrderParamsDto.class);
        }
        return orderParamsDto;
    }

    private OrderParams convertToEntity(OrderParamsDto orderParamsDto) {
        OrderParams orderParams = null;
        if (orderParamsDto != null) {
            orderParams = modelMapper.map(orderParamsDto, OrderParams.class);
        } else {

        }
        return orderParams;
    }

}
