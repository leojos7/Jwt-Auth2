package com.siemens.mindsphere.apps.modules.product.service.paramDetails;

import com.siemens.mindsphere.apps.modules.product.entity.ParamDetails;
import com.siemens.mindsphere.apps.modules.product.repository.ParamDetailsRespository;
import com.siemens.mindsphere.apps.modules.product.service.productParams.ProductParamsService;
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
public class ParamDetailsServiceImpl implements ParamDetailsService {

    @Autowired
    private ParamDetailsRespository paramDetailsRepository;

    @Autowired
    private ProductParamsService productParamsService;

    @Override
    public ParamDetails addParamDetails(ParamDetails product) {
        return paramDetailsRepository.save(product);
    }

    @Override
    public ParamDetails updateParamDetails(ParamDetails paramDetails) {
        Optional<ParamDetails> paramDetailsOptional = paramDetailsRepository.findById(paramDetails.getParamDetailId());
        ParamDetails existingParamDetails = null;
        ParamDetails newParamDetails = null;
        if (paramDetailsOptional.isPresent()) {
            existingParamDetails = paramDetailsOptional.get();
            existingParamDetails.setCode(paramDetails.getCode());
            existingParamDetails.setValue(paramDetails.getValue());
            existingParamDetails.setModifiedDate(new Date());
            productParamsService.updateProductParams(paramDetails.getProductParams());
            newParamDetails = paramDetailsRepository.save(existingParamDetails);
        } else {
            newParamDetails = paramDetailsRepository.save(paramDetails);
        }
        return newParamDetails;
    }

    @Override
    public ParamDetails getParamDetail(Integer paramDetailsId) {
        Optional<ParamDetails> existingParamDetailsOptional = paramDetailsRepository.findById(paramDetailsId);
        ParamDetails existingParamDetails = null;
        if (existingParamDetailsOptional.isPresent()) {
            existingParamDetails = existingParamDetailsOptional.get();
        } else {
            // TODO throw custom exception
        }
        return existingParamDetails;
    }

    @Override
    public void deleteParamDetail(Integer paramDetailsId) {
        if (paramDetailsRepository.findById(paramDetailsId) != null) {
            paramDetailsRepository.deleteById(paramDetailsId);
        } else {
            // TODO throw custom exception
        }
    }

    @Override
    public Page<ParamDetails> getAllParamDetails(Pageable pageable) {
        return paramDetailsRepository.findAll(pageable);
    }

    public void deleteAllParamDetails(Set<ParamDetails> productParamsIds) {
        productParamsIds.stream()
                .forEach(paramDetails -> paramDetails.setProductParams(null));
        paramDetailsRepository.deleteAll(productParamsIds);
    }

}
