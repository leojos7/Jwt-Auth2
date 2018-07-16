package com.siemens.mindsphere.apps.modules.product.service.paramDetails;

import com.siemens.mindsphere.apps.modules.product.entity.ParamDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface ParamDetailsService {

    ParamDetails addParamDetails(ParamDetails paramDetails);

    ParamDetails updateParamDetails(ParamDetails paramDetails);

    void deleteParamDetail(Integer paramDetailsId);

    ParamDetails getParamDetail(Integer paramDetailsId);

    Page<ParamDetails> getAllParamDetails(Pageable pageable);

    void deleteAllParamDetails(Set<ParamDetails> productParamsIds);

}
