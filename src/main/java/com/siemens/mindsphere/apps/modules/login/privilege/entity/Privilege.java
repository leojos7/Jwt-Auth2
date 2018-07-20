package com.siemens.mindsphere.apps.modules.login.privilege.entity;

import com.siemens.mindsphere.apps.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "PRIVILEGE")
public class Privilege extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRIVILEGE_ID")
    private Integer privilegeId;

    @NotNull
    @Size(min = 0, max = 50)
    private String name;

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
