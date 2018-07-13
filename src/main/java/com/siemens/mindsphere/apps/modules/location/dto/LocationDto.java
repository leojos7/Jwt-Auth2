package com.siemens.mindsphere.apps.modules.location.dto;

import java.util.Set;

public class LocationDto {

    private Integer id;

    private String name;

    private Set<LocationParamsDto> locationParams;

    public LocationDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LocationParamsDto> getLocationParams() {
        return locationParams;
    }

    public void setLocationParams(Set<LocationParamsDto> locationParams) {
        this.locationParams = locationParams;
    }
}
