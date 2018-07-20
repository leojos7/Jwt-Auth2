package com.siemens.mindsphere.apps.modules.login.privilege.repository;

import com.siemens.mindsphere.apps.modules.login.privilege.entity.Privilege;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PrivilegeRepository extends PagingAndSortingRepository<Privilege, Integer> {
}
