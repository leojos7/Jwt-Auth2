package com.siemens.mindsphere.apps.modules.location.entity;

import java.io.Serializable;
import java.util.Objects;

public class LocationParamsPK implements Serializable {

    private Integer location;
    private Integer locationParams;

    public LocationParamsPK() {
    }

    public LocationParamsPK(Integer location, Integer locationParams) {
        this.location = location;
        this.locationParams = locationParams;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getLocationParams() {
        return locationParams;
    }

    public void setLocationParams(Integer locationParams) {
        this.locationParams = locationParams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationParamsPK that = (LocationParamsPK) o;
        return Objects.equals(location, that.location) &&
                Objects.equals(locationParams, that.locationParams);
    }

    @Override
    public int hashCode() {

        return Objects.hash(location, locationParams);
    }
}
