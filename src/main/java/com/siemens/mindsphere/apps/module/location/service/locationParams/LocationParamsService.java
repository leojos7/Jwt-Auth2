package com.siemens.mindsphere.apps.module.location.service.locationParams;

import com.siemens.mindsphere.apps.module.location.entity.LocationParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocationParamsService {

    LocationParams addLocationParams(LocationParams cart);

    void deleteLocationParams(Integer cartId);

    LocationParams updateLocationParams(LocationParams cart);

    LocationParams getLocationParams(Integer locationId);

    Page<LocationParams> getAllLocationParams(Pageable pageable);
}
