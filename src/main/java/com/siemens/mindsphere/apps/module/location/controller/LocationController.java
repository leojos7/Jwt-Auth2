package com.siemens.mindsphere.apps.module.location.controller;

import com.siemens.mindsphere.apps.module.location.dto.LocationDto;
import com.siemens.mindsphere.apps.module.location.entity.Location;
import com.siemens.mindsphere.apps.module.location.service.location.LocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure/user/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public LocationDto addLocation(@RequestBody LocationDto locationDto) {
        Location location = convertToEntity(locationDto);
        Location locationCreated = null;
        if (location != null) {
            locationCreated = locationService.addLocation(location);
        }
        return convertToDto(locationCreated);
    }

    @RequestMapping(value = "/delete/{locationId}", method = RequestMethod.GET)
    public void deleteLocation(@PathVariable int locationId) {
        locationService.deleteLocation(locationId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public LocationDto updateLocation(@RequestBody LocationDto locationDto) {
        Location location = convertToEntity(locationDto);
        Location locationUpdated = null;
        if (location != null) {
            locationUpdated = locationService.updateLocation(location);
        }
        return convertToDto(locationUpdated);
    }
    @RequestMapping(value = "/get/{locationId}", method = RequestMethod.GET)
    public LocationDto getLocation(@PathVariable int locationId) {
        return convertToDto(locationService.getLocation(locationId));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Page<LocationDto> getAllLocations(Pageable pageable) {
        return convertToDtos(locationService.getAllLocations( pageable));
    }

    private Page<LocationDto> convertToDtos(Page<Location> locations) {
        return locations.map(locationToMap -> convertToDto(locationToMap));
    }

    private LocationDto convertToDto(Location location) {
        LocationDto locationDto = null;
        if (location != null) {
            locationDto = modelMapper.map(location, LocationDto.class);
        }
        return locationDto;
    }

    private Location convertToEntity(LocationDto locationDto) {
        Location location = null;
        if (locationDto != null) {
            location = modelMapper.map(locationDto, Location.class);
        } else {

        }
        return location;
    }

}