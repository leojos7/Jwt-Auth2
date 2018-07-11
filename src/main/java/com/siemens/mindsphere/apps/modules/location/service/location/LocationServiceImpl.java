package com.siemens.mindsphere.apps.modules.location.service.location;

import com.siemens.mindsphere.apps.modules.location.entity.Location;
import com.siemens.mindsphere.apps.modules.location.repository.LocationRepository;
import com.siemens.mindsphere.apps.modules.location.service.locationParams.LocationParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationParamsService locationParamsService;

    @Override
    public Location addLocation(Location location) {
        location.setLocationParams(location.getLocationParams().stream()
                .map(locationParams ->  locationParamsService.getLocationParams(locationParams.getId()))
                .collect(Collectors.toSet()));
        Location locationCreated = locationRepository.save(location);
        return getLocation(locationCreated.getId());
    }

    @Override
    public void deleteLocation(Integer locationId) {
        Optional<Location> locationOptional = locationRepository.findById(locationId);
        if (locationOptional.isPresent()) {
            locationRepository.delete(locationOptional.get());
        }
    }

    @Override
    public Location updateLocation(Location location) {
        Optional<Location> locationOptional = locationRepository.findById(location.getId());
        Location existingLocation = null;
        Location newLocation = null;
        if (locationOptional.isPresent()) {
            existingLocation = locationOptional.get();
            existingLocation.setName(location.getName());
            existingLocation.setModifiedDate(new Date());
            location.getLocationParams().stream()
                    .forEach(locationParams -> locationParamsService.updateLocationParams(locationParams));
            newLocation = locationRepository.save(existingLocation);
        } else {
            newLocation = locationRepository.save(location);
        }
        return newLocation;
    }

    @Override
    public Location getLocation(Integer locationId) {
        Location location = null;
        if(locationRepository.findById(locationId).isPresent()) {
            location = locationRepository.findById(locationId).get();
        }
        return location;
    }

    @Override
    public Page<Location> getAllLocations(Pageable pageable) {
        return locationRepository.findAll(pageable);
    }

}
