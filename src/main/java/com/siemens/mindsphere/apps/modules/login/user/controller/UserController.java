package com.siemens.mindsphere.apps.modules.login.user.controller;

import com.siemens.mindsphere.apps.modules.login.user.dto.UserDto;
import com.siemens.mindsphere.apps.modules.login.user.entity.User;
import com.siemens.mindsphere.apps.modules.exception.ResourceNotFoundException;
import com.siemens.mindsphere.apps.exception.ParseException;
import com.siemens.mindsphere.apps.modules.login.user.service.UserService;
import com.siemens.mindsphere.apps.modules.login.utils.CommonUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/secure/user")
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
    public void deleteUser(@PathVariable Integer userId)
            throws ResourceNotFoundException, ParseException {
        userService.deleteUser(userId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public UserDto updateUser(@RequestBody UserDto userDto) throws ResourceNotFoundException {
        User user = convertToEntity(userDto);
        User userCreated = null;
        if (user != null) {
            userCreated = userService.updateUser(user);
        }
        return convertToDto(userCreated);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
    public UserDto getUser(@RequestHeader("Authorization") String authorization)
            throws ResourceNotFoundException, ParseException {
        String username = CommonUtils.getUsernameFromAccessToken(authorization);
        User user = userService.getUserByUsername(username);
        return convertToDto(user);
    }

    @RequestMapping(value = "/getUserByName/{username}", method = RequestMethod.GET, produces = "application/json")
    public UserDto getUserByName(@PathVariable String username) throws ResourceNotFoundException, ParseException {
        User user = userService.getUserByUsername(username);
        return convertToDto(user);
    }

    @RequestMapping(value = "/getUserById/{userId}", method = RequestMethod.GET, produces = "application/json")
    public UserDto getUserById(@PathVariable Integer userId) throws ResourceNotFoundException, ParseException {
        User user = userService.getUserById(userId);
        return convertToDto(user);
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
