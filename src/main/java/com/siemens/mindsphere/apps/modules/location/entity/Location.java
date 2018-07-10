package com.siemens.mindsphere.apps.modules.location.entity;

import com.siemens.mindsphere.apps.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "LOCATION")
public class Location extends BaseEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String name;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "LOCATION_PARAM_MAP",
            joinColumns = {@JoinColumn(name = "LOCATION_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "LOCATION_PARAM_ID", referencedColumnName = "id")})
    private Set<LocationParams> locationParams;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LocationParams> getLocationParams() {
        return locationParams;
    }

    public void setLocationParams(Set<LocationParams> locationParams) {
        this.locationParams = locationParams;
    }

}
