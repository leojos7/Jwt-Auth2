package com.siemens.mindsphere.apps.modules.location.repository;

import com.siemens.mindsphere.apps.modules.location.entity.LocationParams;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LocationParamsRepository extends PagingAndSortingRepository<LocationParams, Integer> {
}
