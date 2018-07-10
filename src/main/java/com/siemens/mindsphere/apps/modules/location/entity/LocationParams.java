package com.siemens.mindsphere.apps.modules.location.entity;

import com.siemens.mindsphere.apps.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "LOCATION_PARAMS")
public class LocationParams  extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
