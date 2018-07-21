package com.siemens.mindsphere.apps.modules.login.user.controller;

import com.siemens.mindsphere.apps.common.dto.ResponseDto;
import com.siemens.mindsphere.apps.exceptionHandler.ParseException;
import com.siemens.mindsphere.apps.common.exception.ResourceNotFoundException;
import com.siemens.mindsphere.apps.modules.login.user.dto.UserDto;
import com.siemens.mindsphere.apps.modules.login.user.entity.User;
import com.siemens.mindsphere.apps.modules.login.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/secure/admin")
public class AdminController {

    @Autowired
    public UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
    public Page<UserDto> getAllUsers(Pageable pageable) {
        return convertToDtos(userService.getAllUsers(pageable));
    }

    @RequestMapping(value = "/updateUser",
            method = RequestMethod.POST)
    public UserDto updateRoleOfUsers(@RequestBody UserDto userDto) throws ResourceNotFoundException {
        User user = convertToEntity(userDto);
        return convertToDto(userService.updateUserRole(user));
    }

    @RequestMapping(value = "/activateUser",
            method = RequestMethod.POST)
    public ResponseDto activateUser(@RequestBody UserDto userDto)
            throws ResourceNotFoundException, ParseException {
        ResponseDto responseDto = new ResponseDto();
        if(userDto.getUsername() != null) {
            responseDto.setMessage(userService.activateUser(userDto.getUsername(), userDto.isStatus()));
        }
        return responseDto;
    }

    private Page<UserDto> convertToDtos(Page<User> users) {
        return users.map(usetToMap -> convertToDto(usetToMap));
    }

    private User convertToEntity(UserDto userDto) {
        User user = null;
        if (userDto != null) {
            user = modelMapper.map(userDto, User.class);
        } else {

        }
        return user;
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = null;
        if (user != null) {
            userDto = modelMapper.map(user, UserDto.class);
        }
        return userDto;
    }
}
