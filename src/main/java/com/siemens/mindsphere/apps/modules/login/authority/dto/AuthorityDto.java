package com.siemens.mindsphere.apps.modules.login.authority.dto;

import com.siemens.mindsphere.apps.modules.login.privilege.dto.PrivilegeDto;
import com.siemens.mindsphere.apps.modules.login.privilege.entity.Privilege;

import java.util.Date;
import java.util.Set;

public class AuthorityDto {

    private Integer authorityId;

    private String name;

    private Set<PrivilegeDto> privileges;

    private Date createDate;

    private Date modifiedDate;

    public AuthorityDto() {
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PrivilegeDto> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<PrivilegeDto> privileges) {
        this.privileges = privileges;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
