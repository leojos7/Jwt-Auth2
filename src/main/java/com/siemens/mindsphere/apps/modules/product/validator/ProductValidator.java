package com.siemens.mindsphere.apps.modules.product.validator;

import com.siemens.mindsphere.apps.modules.product.dto.ParamDetailsDto;
import com.siemens.mindsphere.apps.modules.product.dto.ProductDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProductDto productDto = (ProductDto) o;

        for(ParamDetailsDto paramDetailsDto : productDto.getParamDetails()) {
            if(paramDetailsDto.getParamDetailId() != null) {
                errors.reject("10001");
            }
        }
    }
}
