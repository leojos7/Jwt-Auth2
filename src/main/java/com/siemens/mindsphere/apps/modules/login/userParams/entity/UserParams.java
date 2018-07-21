package com.siemens.mindsphere.apps.modules.login.userParams.entity;

import com.siemens.mindsphere.apps.common.entity.BaseParamsEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "USER_PARAM")
public class UserParams extends BaseParamsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_PARAM_ID")
    private Integer userParamId;

    public Integer getUserParamId() {
        return userParamId;
    }

    public void setUserParamId(Integer userParamId) {
        this.userParamId = userParamId;
    }
}
