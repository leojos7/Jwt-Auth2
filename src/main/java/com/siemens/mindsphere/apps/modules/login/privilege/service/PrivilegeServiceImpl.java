package com.siemens.mindsphere.apps.modules.login.privilege.service;

import com.siemens.mindsphere.apps.modules.login.privilege.entity.Privilege;
import com.siemens.mindsphere.apps.modules.login.privilege.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    public Privilege addPrivilege(Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    @Override
    public void deletePrivilege(Integer privilegeId) {
        Optional<Privilege> privilegeOptional = privilegeRepository.findById(privilegeId);
        if (privilegeOptional.isPresent()) {
            privilegeRepository.delete(privilegeOptional.get());
        }
    }

    @Override
    public Privilege updatePrivilege(Privilege privilege) {
        Optional<Privilege> privilegeOptional = privilegeRepository.findById(privilege.getPrivilegeId());
        Privilege existingPrivilege = null;
        Privilege newPrivilege = null;
        if (privilegeOptional.isPresent()) {
            existingPrivilege = privilegeOptional.get();
            existingPrivilege.setName(privilege.getName());
            existingPrivilege.setModifiedDate(new Date());
            newPrivilege = privilegeRepository.save(existingPrivilege);
        } else {
            newPrivilege = privilegeRepository.save(privilege);
        }
        return newPrivilege;
    }

    @Override
    public Privilege getPrivilege(Integer privilegeId) {
        Privilege privilege = null;
        if(privilegeRepository.findById(privilegeId).isPresent()) {
            privilege = privilegeRepository.findById(privilegeId).get();
        }
        return privilege;
    }

    @Override
    public Page<Privilege> getAllPrivileges(Pageable pageable) {
        return privilegeRepository.findAll(pageable);
    }

}
