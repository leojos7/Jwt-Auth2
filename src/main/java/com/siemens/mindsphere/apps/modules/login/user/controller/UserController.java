package com.siemens.mindsphere.apps.modules.login.user.controller;

import com.siemens.mindsphere.apps.common.dto.ResponseDto;
import com.siemens.mindsphere.apps.exceptionHandler.ParseException;
import com.siemens.mindsphere.apps.common.exception.ResourceNotFoundException;
import com.siemens.mindsphere.apps.modules.login.user.dto.UserDto;
import com.siemens.mindsphere.apps.modules.login.user.entity.User;
import com.siemens.mindsphere.apps.modules.login.user.service.UserService;
import com.siemens.mindsphere.apps.common.utils.CommonUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/secure/user")
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommonUtils commonUtils;

    @RequestMapping(value = "/delete/{userId}",
            method = RequestMethod.GET)
    public ResponseDto deleteUser(@PathVariable Integer userId)
            throws ResourceNotFoundException, ParseException {
        ResponseDto responseDto = new ResponseDto();
        if(userId != null) {
            responseDto.setMessage(userService.deleteUser(userId));
        }
        return responseDto;
    }

    @RequestMapping(value = "/update",
            method = RequestMethod.POST,
            produces = "application/json")
    public UserDto updateUser(@RequestBody UserDto userDto) throws ResourceNotFoundException {
        User user = convertToEntity(userDto);
        User userCreated = null;
        if (user != null) {
            userCreated = userService.updateUser(user);
        }
        return convertToDto(userCreated);
    }

    @RequestMapping(value = "/get",
            method = RequestMethod.GET,
            produces = "application/json")
    public UserDto getUser(@RequestHeader("Authorization") String authorization)
            throws ResourceNotFoundException, ParseException {
        String username = commonUtils.getUsernameFromAccessToken(authorization);
        User user = userService.getUserByUsername(username);
        return convertToDto(user);
    }

    @RequestMapping(value = "/getUserByName/{username}",
            method = RequestMethod.GET,
            produces = "application/json")
    public UserDto getUserByName(@PathVariable String username) throws ResourceNotFoundException, ParseException {
        User user = userService.getUserByUsername(username);
        return convertToDto(user);
    }

    @RequestMapping(value = "/getUserById/{userId}",
            method = RequestMethod.GET,
            produces = "application/json")
    public UserDto getUserById(@PathVariable Integer userId) throws ResourceNotFoundException, ParseException {
        User user = userService.getUserById(userId);
        return convertToDto(user);
    }

    @RequestMapping(value = "/sentOtp",
            method = RequestMethod.GET)
    public ResponseDto setOtp(@RequestHeader("Authorization") String authorization) throws ResourceNotFoundException, ParseException {
        String username = commonUtils.getUsernameFromAccessToken(authorization);
        ResponseDto responseDto = new ResponseDto();
        if(username != null) {
            responseDto.setMessage(userService.sentOtp(username));
        }
        return responseDto;
    }


    private UserDto convertToDto(User user) {
        UserDto userDto = null;
        if (user != null) {
            userDto = modelMapper.map(user, UserDto.class);
        }
        return userDto;
    }

    private User convertToEntity(UserDto userDto) {
        User user = null;
        if (userDto != null) {
            user = modelMapper.map(userDto, User.class);
        } else {

        }
        return user;
    }

}
