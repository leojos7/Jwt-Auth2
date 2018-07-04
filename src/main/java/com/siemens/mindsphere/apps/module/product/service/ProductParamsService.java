package com.siemens.mindsphere.apps.module.product.service;

import com.siemens.mindsphere.apps.module.product.entity.ProductParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductParamsService {

    public Page<ProductParams> getAllProductParams(Pageable pageable);

    public ProductParams getProductParam(Integer productParamId);
}
