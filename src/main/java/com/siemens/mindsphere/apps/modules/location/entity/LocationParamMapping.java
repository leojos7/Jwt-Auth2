package com.siemens.mindsphere.apps.modules.location.entity;

import com.siemens.mindsphere.apps.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="LOCATION_PARAM_MAP")
@IdClass(LocationParamsPK.class)
public class LocationParamMapping extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @Id
    @ManyToOne
    @JoinColumn(name = "LOCATION_PARAM_ID")
    private LocationParams locationParams;

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
