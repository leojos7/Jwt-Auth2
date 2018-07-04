package com.siemens.mindsphere.apps.module.product.service;

import com.siemens.mindsphere.apps.module.product.entity.ProductParams;
import com.siemens.mindsphere.apps.module.product.repository.ProductParamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductParamsServiceImpl implements ProductParamsService{

    @Autowired
    private ProductParamsRepository productParamsRepository;

    @Override
    public Page<ProductParams> getAllProductParams(Pageable pageable) {
        return productParamsRepository.findAll(pageable);
    }

    @Override
    public ProductParams getProductParam(Integer productParamId) {
        Optional<ProductParams> existingProductParamOptional = productParamsRepository.findById(productParamId);
        ProductParams existingProductParam = null;
        if(existingProductParamOptional.isPresent()) {
            existingProductParam = existingProductParamOptional.get();
        } else {
            // TODO throw custom exception
        }
        return existingProductParam;
    }

}
