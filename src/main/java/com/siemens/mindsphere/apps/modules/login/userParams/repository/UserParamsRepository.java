package com.siemens.mindsphere.apps.modules.login.userParams.repository;

import com.siemens.mindsphere.apps.modules.login.userParams.entity.UserParams;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserParamsRepository extends PagingAndSortingRepository<UserParams, Integer> {
}
