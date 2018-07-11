package com.siemens.mindsphere.apps.modules.location.controller;

import com.siemens.mindsphere.apps.modules.location.dto.LocationParamsDto;
import com.siemens.mindsphere.apps.modules.location.entity.LocationParams;
import com.siemens.mindsphere.apps.modules.location.service.locationParams.LocationParamsService;
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
    public LocationParamsDto addLocationParam(@RequestBody LocationParamsDto locationParamsDto) {
        LocationParams locationParams = convertToEntity(locationParamsDto);
        LocationParams locationParamsCreated = null;
        if (locationParams != null) {
            locationParamsCreated = locationParamsService.addLocationParams(locationParams);
        }
        return convertToDto(locationParamsCreated);
    }

    @RequestMapping(value = "/delete/{locationId}", method = RequestMethod.GET)
    public void deleteLocationParam(@PathVariable int locationId) {
        locationParamsService.deleteLocationParams(locationId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public LocationParamsDto updateLocationParam(@RequestBody LocationParamsDto locationParamsDto) {
        LocationParams locationParams = convertToEntity(locationParamsDto);
        LocationParams locationParamsUpdated = null;
        if (locationParams != null) {
            locationParamsUpdated = locationParamsService.updateLocationParams(locationParams);
        }
        return convertToDto(locationParamsUpdated);
    }
    @RequestMapping(value = "/get/{locationParamsId}", method = RequestMethod.GET)
    public LocationParamsDto getLocationParam(@PathVariable int locationParamsId) {
        return convertToDto(locationParamsService.getLocationParams(locationParamsId));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Page<LocationParamsDto> getAllLocationParams(Pageable pageable) {
        return convertToDtos(locationParamsService.getAllLocationParams(pageable));
    }

    private Page<LocationParamsDto> convertToDtos(Page<LocationParams> locationParams) {
        return locationParams.map(locationParamToMap -> convertToDto(locationParamToMap));
    }

    private LocationParamsDto convertToDto(LocationParams locationParams) {
        LocationParamsDto locationParamsDto = null;
        if (locationParams != null) {
            locationParamsDto = modelMapper.map(locationParams, LocationParamsDto.class);
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
