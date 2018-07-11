package com.siemens.mindsphere.apps.modules.location.service.locationParams;

import com.siemens.mindsphere.apps.modules.location.entity.LocationParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocationParamsService {

    LocationParams addLocationParams(LocationParams locationParams);

    void deleteLocationParams(Integer locationParamsId);

    LocationParams updateLocationParams(LocationParams locationParams);

    LocationParams getLocationParams(Integer locationParamsId);

    Page<LocationParams> getAllLocationParams(Pageable pageable);
}
