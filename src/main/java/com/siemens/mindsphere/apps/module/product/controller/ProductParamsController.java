package com.siemens.mindsphere.apps.module.product.controller;

import com.siemens.mindsphere.apps.module.product.dto.ProductParamsDto;
import com.siemens.mindsphere.apps.module.product.entity.ProductParams;
import com.siemens.mindsphere.apps.module.product.service.ProductParamsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/secure/user/productParams")
public class ProductParamsController {

    @Autowired
    private ProductParamsService productParamsService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/getAllProductParams", method = RequestMethod.GET, produces = "application/json")
    public Page<ProductParamsDto> getAllProductParams(Pageable pageable) {
        return convertToDtos(productParamsService.getAllProductParams(pageable));
    }

    @RequestMapping(value = "/get/{productId}", method = RequestMethod.GET)
    public ProductParamsDto getProduct(@PathVariable int productId) {
        return convertToDto(productParamsService.getProductParam(productId));
    }

    private Page<ProductParamsDto> convertToDtos(Page<ProductParams> productParams) {
        return productParams.map(productParam -> convertToDto(productParam));
    }

    private ProductParamsDto convertToDto(ProductParams product) {
        ProductParamsDto productParamsDto = modelMapper.map(product, ProductParamsDto.class);
        return productParamsDto;
    }

    private ProductParams convertToEntity(ProductParamsDto productDto) {
        ProductParams productParams = modelMapper.map(productDto, ProductParams.class);
        return productParams;
    }


}
