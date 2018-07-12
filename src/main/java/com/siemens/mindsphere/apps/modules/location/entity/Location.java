package com.siemens.mindsphere.apps.modules.location.entity;

import com.siemens.mindsphere.apps.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "LOCATION")
public class Location extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATION_ID")
    private Integer locationId;

    private String name;

    @OneToMany(mappedBy = "locationParamsPK.locationParams", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<LocationParamMapping> locationParamMapping;

    public Location() {
        super();
    }

    public Location(String name) {
        this.name = name;
    }

    public Location(Integer locationId, String name, Set<LocationParamMapping> locationParamMapping) {
        this.locationId = locationId;
        this.name = name;
        this.locationParamMapping = locationParamMapping;
    }


    public Location(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LocationParamMapping> getLocationParamMapping() {
        return locationParamMapping;
    }

    public void setLocationParamMapping(Set<LocationParamMapping> locationParamMapping) {
        this.locationParamMapping = locationParamMapping;
    }


}
