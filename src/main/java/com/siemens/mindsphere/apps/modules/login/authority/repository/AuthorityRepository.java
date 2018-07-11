package com.siemens.mindsphere.apps.modules.login.authority.repository;

import com.siemens.mindsphere.apps.modules.login.authority.entity.Authority;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorityRepository extends PagingAndSortingRepository<Authority, Integer> {
}
