package com.siemens.mindsphere.apps.modules.location.entity;

import com.siemens.mindsphere.apps.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="location_param_map")
@AssociationOverrides({
        @AssociationOverride(name = "locationParamsPK.location",
                joinColumns = @JoinColumn(name = "LOCATION_ID")),
        @AssociationOverride(name = "locationParamsPK.locationParams",
                joinColumns = @JoinColumn(name = "LOCATION_PARAM_ID")) })
public class LocationParamMapping extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private LocationParamsPK locationParamsPK;

    public LocationParamMapping() {
    }

    public LocationParamsPK getLocationParamsPK() {
        return locationParamsPK;
    }

    public void setLocationParamsPK(LocationParamsPK locationParamsPK) {
        this.locationParamsPK = locationParamsPK;
    }

    @Transient
    public Location getLocation() {
        return getLocationParamsPK().getLocation();
    }

    public void setLocation(Location location) {
        getLocationParamsPK().setLocation(location);
    }

    @Transient
    public LocationParams getLocationParams() {
        return getLocationParamsPK().getLocationParams();
    }

    public void setLocationParams(LocationParams locationParams) {
        getLocationParamsPK().setLocationParams(locationParams);
    }
}
