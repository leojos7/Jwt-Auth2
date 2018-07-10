package com.siemens.mindsphere.apps.modules.login.controller;

import com.siemens.mindsphere.apps.modules.login.dto.UserParamsDto;
import com.siemens.mindsphere.apps.modules.login.entity.UserParams;
import com.siemens.mindsphere.apps.modules.login.service.userParams.UserParamsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure/user/userParams")
public class UserParamsController {

    @Autowired
    private UserParamsService userParamsService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UserParamsDto addUserParams(@RequestBody UserParamsDto userParamsDto) {
        UserParams userParams = convertToEntity(userParamsDto);
        UserParams newUserParams = null;
        if (userParams != null) {
            newUserParams = userParamsService.addUserParams(userParams);
        }
        return convertToDto(newUserParams);
    }

    @RequestMapping(value = "/delete/{userParamsId}", method = RequestMethod.GET)
    public void deleteUserParams(@PathVariable int userParamsId) {
        userParamsService.deleteUserParams(userParamsId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public UserParamsDto updateUserParams(@RequestBody UserParamsDto userParamsDto) {
        UserParams userParams = convertToEntity(userParamsDto);
        UserParams newUserParams = null;
        if (userParams != null) {
            newUserParams = userParamsService.updateUserParams(userParams);
        }
        return convertToDto(newUserParams);
    }
    @RequestMapping(value = "/get/{userParamsId}", method = RequestMethod.GET)
    public UserParamsDto getUserParams(@PathVariable int userParamsId) {
        return convertToDto(userParamsService.getUserParams(userParamsId));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Page<UserParamsDto> getAllUserParams(Pageable pageable) {
        return convertToDtos(userParamsService.getAllUserParams(pageable));
    }

    private Page<UserParamsDto> convertToDtos(Page<UserParams> userParams) {
        return userParams.map(userParamsToMap -> convertToDto(userParamsToMap));
    }

    private UserParamsDto convertToDto(UserParams userParams) {
        UserParamsDto userParamsDto = null;
        if (userParams != null) {
            userParamsDto = modelMapper.map(userParams, UserParamsDto.class);
        }
        return userParamsDto;
    }

    private UserParams convertToEntity(UserParamsDto userParamsDto) {
        UserParams userParams = null;
        if (userParamsDto != null) {
            userParams = modelMapper.map(userParamsDto, UserParams.class);
        } else {

        }
        return userParams;
    }

}
