package com.siemens.mindsphere.apps.modules.product.service.productParams;

import com.siemens.mindsphere.apps.modules.product.entity.ProductParams;
import com.siemens.mindsphere.apps.modules.product.repository.ProductParamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ProductParamsServiceImpl implements ProductParamsService {

    @Autowired
    private ProductParamsRepository productParamsRepository;

    @Override
    public ProductParams addProductParams(ProductParams product) {
        return productParamsRepository.save(product);
    }

    @Override
    public ProductParams updateProductParams(ProductParams productParams) {
        Optional<ProductParams> paramDetailsOptional = productParamsRepository.findById(productParams.getProductParamId());
        ProductParams existingProductParams = null;
        ProductParams newProductParams = null;
        if (paramDetailsOptional.isPresent()) {
            existingProductParams = paramDetailsOptional.get();
            existingProductParams.setName(productParams.getName());
            existingProductParams.setDescription(productParams.getDescription());
            existingProductParams.setModifiedDate(new Date());
            newProductParams = productParamsRepository.save(existingProductParams);
        } else {
            newProductParams = productParamsRepository.save(productParams);
        }
        return newProductParams;
    }

    @Override
    public ProductParams getProductParam(Integer productParamId) {
        Optional<ProductParams> existingProductParamOptional = productParamsRepository.findById(productParamId);
        ProductParams existingProductParam = null;
        if (existingProductParamOptional.isPresent()) {
            existingProductParam = existingProductParamOptional.get();
        } else {
            // TODO throw custom exception
        }
        return existingProductParam;
    }

    @Override
    public void deleteProductParams(Integer paramDetailsId) {
        if (productParamsRepository.findById(paramDetailsId) != null) {
            productParamsRepository.deleteById(paramDetailsId);
        } else {
            // TODO throw custom exception
        }
    }

    @Override
    public Page<ProductParams> getAllProductParams(Pageable pageable) {
        return productParamsRepository.findAll(pageable);
    }

    public void deleteAllProductParams(Set<ProductParams> productParamsIds) {
        productParamsRepository.deleteAll(productParamsIds);
    }

}
