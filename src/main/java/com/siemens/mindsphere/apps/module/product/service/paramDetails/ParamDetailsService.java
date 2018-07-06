package com.siemens.mindsphere.apps.module.product.service.paramDetails;

import com.siemens.mindsphere.apps.module.product.entity.ParamDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface ParamDetailsService {

    ParamDetails addParamDetails(ParamDetails product);

    ParamDetails updateParamDetails(ParamDetails product);

    void deleteParamDetail(Integer paramDetailsId);

    ParamDetails getParamDetail(Integer paramDetailsId);

    Page<ParamDetails> getAllParamDetails(Pageable pageable);

    void deleteAllParamDetails(Set<ParamDetails> productParamsIds);

}
