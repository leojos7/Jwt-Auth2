package com.siemens.mindsphere.apps.module.location.controller;

import com.siemens.mindsphere.apps.module.location.dto.LocationParamsDto;
import com.siemens.mindsphere.apps.module.location.entity.LocationParams;
import com.siemens.mindsphere.apps.module.location.service.locationParams.LocationParamsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure/user/locationParams")
public class LocationParamsController {

    @Autowired
    private LocationParamsService locationParamsService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public LocationParamsDto addLocation(@RequestBody LocationParamsDto locationParamsDto) {
        LocationParams location = convertToEntity(locationParamsDto);
        LocationParams locationParams = null;
        if (location != null) {
            locationParams = locationParamsService.addLocationParams(location);
        }
        return convertToDto(locationParams);
    }

    @RequestMapping(value = "/delete/{locationId}", method = RequestMethod.GET)
    public void deleteLocation(@PathVariable int locationId) {
        locationParamsService.deleteLocationParams(locationId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public LocationParamsDto updateLocation(@RequestBody LocationParamsDto locationParamsDto) {
        LocationParams location = convertToEntity(locationParamsDto);
        LocationParams locationUpdated = null;
        if (location != null) {
            locationUpdated = locationParamsService.updateLocationParams(location);
        }
        return convertToDto(locationUpdated);
    }
    @RequestMapping(value = "/get/{locationParamsId}", method = RequestMethod.GET)
    public LocationParamsDto getLocation(@PathVariable int locationParamsId) {
        return convertToDto(locationParamsService.getLocationParams(locationParamsId));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Page<LocationParamsDto> getAllLocations(Pageable pageable) {
        return convertToDtos(locationParamsService.getAllLocationParams(pageable));
    }

    private Page<LocationParamsDto> convertToDtos(Page<LocationParams> locations) {
        return locations.map(locationsToMap -> convertToDto(locationsToMap));
    }

    private LocationParamsDto convertToDto(LocationParams location) {
        LocationParamsDto locationParamsDto = null;
        if (location != null) {
            locationParamsDto = modelMapper.map(location, LocationParamsDto.class);
        }
        return locationParamsDto;
    }

    private LocationParams convertToEntity(LocationParamsDto locationParamsDto) {
        LocationParams locationParams = null;
        if (locationParamsDto != null) {
            locationParams = modelMapper.map(locationParamsDto, LocationParams.class);
        } else {

        }
        return locationParams;
    }

}
