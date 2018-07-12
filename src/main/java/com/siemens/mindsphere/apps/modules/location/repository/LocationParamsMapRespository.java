package com.siemens.mindsphere.apps.modules.location.repository;

import com.siemens.mindsphere.apps.modules.location.entity.LocationParamMapping;
import com.siemens.mindsphere.apps.modules.location.entity.LocationParamsPK;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LocationParamsMapRespository extends PagingAndSortingRepository<LocationParamMapping, LocationParamsPK> {
}
