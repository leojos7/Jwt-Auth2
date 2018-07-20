package com.siemens.mindsphere.apps.modules.login.privilege.controller;

import com.siemens.mindsphere.apps.modules.login.privilege.dto.PrivilegeDto;
import com.siemens.mindsphere.apps.modules.login.privilege.entity.Privilege;
import com.siemens.mindsphere.apps.modules.login.privilege.service.PrivilegeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure/admin/privilege")
public class PrivilegeController {

    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/add",
            method = RequestMethod.POST)
    public PrivilegeDto addPrivilege(@RequestBody PrivilegeDto privilegeDto) {
        Privilege privilege = convertToEntity(privilegeDto);
        Privilege privilegeCreated = null;
        if (privilege != null) {
            privilegeCreated = privilegeService.addPrivilege(privilege);
        }
        return convertToDto(privilegeCreated);
    }

    @RequestMapping(value = "/delete/{privilegeId}",
            method = RequestMethod.GET)
    public void deletePrivilege(@PathVariable int privilegeId) {
        privilegeService.deletePrivilege(privilegeId);
    }

    @RequestMapping(value = "/update",
            method = RequestMethod.POST)
    public PrivilegeDto updatePrivilege(@RequestBody PrivilegeDto privilegeDto) {
        Privilege privilege = convertToEntity(privilegeDto);
        Privilege privilegeUpdated = null;
        if (privilege != null) {
            privilegeUpdated = privilegeService.updatePrivilege(privilege);
        }
        return convertToDto(privilegeUpdated);
    }
    @RequestMapping(value = "/get/{privilegeId}",
            method = RequestMethod.GET)
    public PrivilegeDto getPrivilege(@PathVariable int privilegeId) {
        return convertToDto(privilegeService.getPrivilege(privilegeId));
    }

    @RequestMapping(value = "/get",
            method = RequestMethod.GET)
    public Page<PrivilegeDto> getAllPrivileges(Pageable pageable) {
        return convertToDtos(privilegeService.getAllPrivileges(pageable));
    }

    private Page<PrivilegeDto> convertToDtos(Page<Privilege> authorities) {
        return authorities.map(privilegeToMap -> convertToDto(privilegeToMap));
    }

    private PrivilegeDto convertToDto(Privilege privilege) {
        PrivilegeDto privilegeDto = null;
        if (privilege != null) {
            privilegeDto = modelMapper.map(privilege, PrivilegeDto.class);
        }
        return privilegeDto;
    }

    private Privilege convertToEntity(PrivilegeDto privilegeDto) {
        Privilege privilege = null;
        if (privilegeDto != null) {
            privilege = modelMapper.map(privilegeDto, Privilege.class);
        } else {

        }
        return privilege;
    }

}
