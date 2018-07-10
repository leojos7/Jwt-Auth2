package com.siemens.mindsphere.apps.modules.product.service.productParams;

import com.siemens.mindsphere.apps.modules.product.entity.ProductParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface ProductParamsService {

    ProductParams addProductParams(ProductParams product);

    ProductParams updateProductParams(ProductParams product);

    void deleteProductParams(Integer paramDetailsId);

    ProductParams getProductParam(Integer productParamId);

    Page<ProductParams> getAllProductParams(Pageable pageable);

    void deleteAllProductParams(Set<ProductParams> productParamsIds);

}
