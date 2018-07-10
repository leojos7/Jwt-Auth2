package com.siemens.mindsphere.apps.modules.product.controller;

import com.siemens.mindsphere.apps.modules.product.dto.ProductParamsDto;
import com.siemens.mindsphere.apps.modules.product.entity.ProductParams;
import com.siemens.mindsphere.apps.modules.product.service.productParams.ProductParamsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure/user/productParams")
public class ProductParamsController {

    @Autowired
    private ProductParamsService productParamsService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ProductParamsDto addProductParams(@RequestBody ProductParamsDto productDto) {
        ProductParams productParams = convertToEntity(productDto);
        ProductParams productParamsCreated = null;
        if (productParams != null) {
            productParamsCreated = productParamsService.addProductParams(productParams);
        }
        return convertToDto(productParamsCreated);
    }

    @RequestMapping(value = "/get/{productParamsId}", method = RequestMethod.GET)
    public ProductParamsDto getProductParams(@PathVariable int productParamsId) {
        return convertToDto(productParamsService.getProductParam(productParamsId));
    }

    @RequestMapping(value = "/delete/{productParamsId}", method = RequestMethod.GET)
    public void deleteProductParams(@PathVariable int productParamsId) {
        productParamsService.deleteProductParams(productParamsId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ProductParamsDto updateProductParams(@RequestBody ProductParamsDto productDto) {
        ProductParams productParams = convertToEntity(productDto);
        ProductParams productParamsUpdated = null;
        if (productParams != null) {
            productParamsUpdated = productParamsService.updateProductParams(productParams);
        }
        return convertToDto(productParamsUpdated);
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
        if (productParams != null) {
            productParamsDto = modelMapper.map(productParams, ProductParamsDto.class);
        }
        return productParamsDto;
    }

    private ProductParams convertToEntity(ProductParamsDto productDto) {
        ProductParams productParams = null;
        if (productDto != null) {
            productParams = modelMapper.map(productDto, ProductParams.class);
        }
        return productParams;
    }


}
