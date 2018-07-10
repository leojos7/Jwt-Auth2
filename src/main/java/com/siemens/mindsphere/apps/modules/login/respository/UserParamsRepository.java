package com.siemens.mindsphere.apps.modules.login.respository;

import com.siemens.mindsphere.apps.modules.login.entity.UserParams;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserParamsRepository extends PagingAndSortingRepository<UserParams, Integer> {
}
