package com.siemens.mindsphere.apps.modules.location.repository;

import com.siemens.mindsphere.apps.modules.location.entity.Location;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface LocationRepository extends PagingAndSortingRepository<Location, Integer> {
}
