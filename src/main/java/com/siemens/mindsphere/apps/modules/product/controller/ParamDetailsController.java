package com.siemens.mindsphere.apps.modules.product.controller;

import com.siemens.mindsphere.apps.modules.product.dto.ParamDetailsDto;
import com.siemens.mindsphere.apps.modules.product.entity.ParamDetails;
import com.siemens.mindsphere.apps.modules.product.service.paramDetails.ParamDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin
@RestController
@RequestMapping("/secure/user/paramDetails")
public class ParamDetailsController {

    @Autowired
    private ParamDetailsService paramDetailsService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ParamDetailsDto addParamDetails(@RequestBody ParamDetailsDto productDto) throws ParseException {
        ParamDetails paramDetails = convertToEntity(productDto);
        ParamDetails paramDetailsCreated = null;
        if (paramDetails != null) {
            paramDetailsCreated = paramDetailsService.addParamDetails(paramDetails);
        }
        return convertToDto(paramDetailsCreated);
    }

    @RequestMapping(value = "/get/{paramDetailId}",
            method = RequestMethod.GET,
            produces = "application/json")
    public ParamDetailsDto getParamDetails(@PathVariable int paramDetailId) {
        return convertToDto(paramDetailsService.getParamDetail(paramDetailId));
    }

    @RequestMapping(value = "/delete/{paramDetailId}",
            method = RequestMethod.GET)
    public void deleteParamDetails(@PathVariable int paramDetailId) {
        paramDetailsService.deleteParamDetail(paramDetailId);
    }

    @RequestMapping(value = "/update",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    public ParamDetailsDto updateParamDetails(@RequestBody ParamDetailsDto productDto) {
        ParamDetails paramDetails = convertToEntity(productDto);
        ParamDetails paramDetailsUpdated = null;
        if (paramDetails != null) {
            paramDetailsUpdated = paramDetailsService.updateParamDetails(paramDetails);
        }
        return convertToDto(paramDetailsUpdated);
    }

    @RequestMapping(value = "/getAllParamDetails",
            method = RequestMethod.GET,
            produces = "application/json")
    public Page<ParamDetailsDto> getAllParamDetails(Pageable pageable) {
        return convertToDtos(paramDetailsService.getAllParamDetails(pageable));
    }

    private Page<ParamDetailsDto> convertToDtos(Page<ParamDetails> paramDetails) {
        return paramDetails.map(paramDetailsToBeConverted -> convertToDto(paramDetailsToBeConverted));
    }

    private ParamDetailsDto convertToDto(ParamDetails product) {
        ParamDetailsDto productDto = null;
        if (product != null) {
            productDto = modelMapper.map(product, ParamDetailsDto.class);
        }
        return productDto;
    }

    private ParamDetails convertToEntity(ParamDetailsDto productDto) {
        ParamDetails product = null;
        if (productDto != null)
            product = modelMapper.map(productDto, ParamDetails.class);
        return product;
    }
}
