package com.siemens.mindsphere.apps.modules.login.privilege.service;

import com.siemens.mindsphere.apps.modules.login.privilege.entity.Privilege;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PrivilegeService {

    Privilege addPrivilege(Privilege privilege);

    void deletePrivilege(Integer privilegeId);

    Privilege updatePrivilege(Privilege privilege);

    Privilege getPrivilege(Integer privilegeId);

    Page<Privilege> getAllPrivileges(Pageable pageable);
}
