package com.siemens.mindsphere.apps.module.product.controller;

import com.siemens.mindsphere.apps.module.product.dto.ProductParamsDto;
import com.siemens.mindsphere.apps.module.product.entity.ProductParams;
import com.siemens.mindsphere.apps.module.product.service.productParams.ProductParamsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/secure/user/productParams")
public class ProductParamsController {

    @Autowired
    private ProductParamsService productParamsService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductParamsDto addParamDetails(@RequestBody ProductParamsDto productDto) {
        ProductParams paramDetails = convertToEntity(productDto);
        ProductParams paramDetailsCreated = null;
        if(paramDetails != null) {
            paramDetailsCreated = productParamsService.addProductParams(paramDetails);
        }
        return convertToDto(paramDetailsCreated);
    }

    @RequestMapping(value = "/get/{productId}", method = RequestMethod.GET)
    public ProductParamsDto getProduct(@PathVariable int productId) {
        return convertToDto(productParamsService.getProductParam(productId));
    }

    @RequestMapping(value = "/delete/{paramDetailId}", method = RequestMethod.GET)
    public void deleteParamDetails(@PathVariable int productId) {
        productParamsService.deleteProductParams(productId);
    }

    @RequestMapping(value = "/getAllProductParams", method = RequestMethod.GET, produces = "application/json")
    public Page<ProductParamsDto> getAllProductParams(Pageable pageable) {
        return convertToDtos(productParamsService.getAllProductParams(pageable));
    }


    private Page<ProductParamsDto> convertToDtos(Page<ProductParams> productParams) {
        return productParams.map(productParamsToBeConverted -> convertToDto(productParamsToBeConverted));
    }

    private ProductParamsDto convertToDto(ProductParams productParams) {
        ProductParamsDto productParamsDto = null;
        if(productParams != null) {
            productParamsDto = modelMapper.map(productParams, ProductParamsDto.class);
        }
        return productParamsDto;
    }

    private ProductParams convertToEntity(ProductParamsDto productDto) {
        ProductParams productParams = null;
        if(productDto != null) {
            productParams = modelMapper.map(productDto, ProductParams.class);
        }
        return productParams;
    }


}
