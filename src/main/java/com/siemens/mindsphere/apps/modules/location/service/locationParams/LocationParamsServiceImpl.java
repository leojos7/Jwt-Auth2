package com.siemens.mindsphere.apps.modules.location.service.locationParams;

import com.siemens.mindsphere.apps.modules.location.entity.LocationParams;
import com.siemens.mindsphere.apps.modules.location.repository.LocationParamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class LocationParamsServiceImpl implements LocationParamsService {

    @Autowired
    private LocationParamsRepository locationParamsRepository;

    @Override
    public LocationParams addLocationParams(LocationParams locationParams) {
        return locationParamsRepository.save(locationParams);
    }

    @Override
    public void deleteLocationParams(Integer locationParamsId) {
        Optional<LocationParams> locationParamsOptional = locationParamsRepository.findById(locationParamsId);
        if (locationParamsOptional.isPresent()) {
            locationParamsRepository.delete(locationParamsOptional.get());
        }
    }

    @Override
    public LocationParams updateLocationParams(LocationParams locationParams) {
        Optional<LocationParams> locationParamsOptional = locationParamsRepository.findById(locationParams.getId());
        LocationParams existingLocationParams = null;
        LocationParams newLocationParams = null;
        if (locationParamsOptional.isPresent()) {
            existingLocationParams = locationParamsOptional.get();
            existingLocationParams.setName(locationParams.getName());
            existingLocationParams.setDescription(locationParams.getDescription());
            existingLocationParams.setModifiedDate(new Date());
            newLocationParams = locationParamsRepository.save(existingLocationParams);
        } else {
            newLocationParams = locationParamsRepository.save(locationParams);
        }
        return newLocationParams;
    }

    @Override
    public LocationParams getLocationParams(Integer locationParamsId) {
        LocationParams locationParams = null;
        if(locationParamsRepository.findById(locationParamsId).isPresent()) {
            locationParams = locationParamsRepository.findById(locationParamsId).get();
        }
        return locationParams;
    }

    @Override
    public Page<LocationParams> getAllLocationParams(Pageable pageable) {
        return locationParamsRepository.findAll(pageable);
    }

}
