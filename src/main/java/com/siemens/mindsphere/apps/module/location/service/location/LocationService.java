package com.siemens.mindsphere.apps.module.location.service.location;

import com.siemens.mindsphere.apps.module.location.entity.Location;
import com.siemens.mindsphere.apps.module.login.exception.NoUserFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocationService {

    Location addLocation(Location cart);

    void deleteLocation(Integer cartId);

    Location updateLocation(Location cart);

    Location getLocation(Integer locationId);

    Page<Location> getAllLocations(Pageable pageable);
}
