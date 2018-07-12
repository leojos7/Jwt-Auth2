package com.siemens.mindsphere.apps.modules.location.entity;

import com.siemens.mindsphere.apps.entity.BaseParamsEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "LOCATION_PARAM")
public class LocationParams extends BaseParamsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATION_PARAM_ID")
    private Integer LocationParamsId;


    public Integer getLocationParamsId() {
        return LocationParamsId;
    }

    public void setLocationParamsId(Integer locationParamsId) {
        LocationParamsId = locationParamsId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}
