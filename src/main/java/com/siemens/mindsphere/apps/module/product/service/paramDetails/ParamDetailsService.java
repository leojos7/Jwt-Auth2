package com.siemens.mindsphere.apps.module.product.service.paramDetails;

import com.siemens.mindsphere.apps.module.product.entity.ParamDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface ParamDetailsService {

    public ParamDetails addParamDetails(ParamDetails product);

    public ParamDetails updateParamDetails(ParamDetails product);

    public void deleteParamDetail(Integer paramDetailsId);

    public ParamDetails getParamDetail(Integer paramDetailsId);

    public Page<ParamDetails> getAllParamDetails(Pageable pageable);

    void deleteAllParamDetails(Set<ParamDetails> productParamsIds);

}
