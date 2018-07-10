package com.siemens.mindsphere.apps.module.login.respository;

import com.siemens.mindsphere.apps.module.login.entity.UserParams;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserParamsRepository extends PagingAndSortingRepository<UserParams, Integer> {
}
