package com.siemens.mindsphere.apps.module.login.repository;


import com.siemens.mindsphere.apps.module.login.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
