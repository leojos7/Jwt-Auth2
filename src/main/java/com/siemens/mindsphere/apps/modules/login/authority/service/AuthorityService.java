package com.siemens.mindsphere.apps.modules.login.authority.service;

import com.siemens.mindsphere.apps.modules.login.authority.entity.Authority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorityService {

    Authority addAuthority(Authority authority);

    void deleteAuthority(Integer authorityId);

    Authority updateAuthority(Authority authority);

    Authority getAuthority(Integer authorityId);

    Page<Authority> getAllAuthorities(Pageable pageable);
}
