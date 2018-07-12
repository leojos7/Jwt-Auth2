package com.siemens.mindsphere.apps.modules.location.entity;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class LocationParamsPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Location location;

    @ManyToOne
    private LocationParams locationParams;

    public LocationParamsPK() {
        super();
    }

    public LocationParamsPK(Location location, LocationParams locationParams) {
        this.location = location;
        this.locationParams = locationParams;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocationParams getLocationParams() {
        return locationParams;
    }

    public void setLocationParams(LocationParams locationParams) {
        this.locationParams = locationParams;
    }
}

