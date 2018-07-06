package com.siemens.mindsphere.apps.module.location.repository;

import com.siemens.mindsphere.apps.module.location.entity.Location;
import com.siemens.mindsphere.apps.module.login.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface LocationRepository extends PagingAndSortingRepository<Location, Integer> {
}
