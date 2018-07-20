package com.siemens.mindsphere.apps.modules.login.user.controller;

import com.siemens.mindsphere.apps.exception.ParseException;
import com.siemens.mindsphere.apps.modules.exception.AlreadyExistingResourceException;
import com.siemens.mindsphere.apps.modules.exception.ResourceNotFoundException;
import com.siemens.mindsphere.apps.modules.login.authority.entity.Authority;
import com.siemens.mindsphere.apps.modules.login.user.dto.UserDto;
import com.siemens.mindsphere.apps.modules.login.user.entity.User;
import com.siemens.mindsphere.apps.modules.login.user.service.UserService;
import com.siemens.mindsphere.apps.modules.login.utils.Authorities;
import com.siemens.mindsphere.apps.modules.login.utils.CommonUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    public UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/sign-up",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public UserDto signUp(@RequestBody UserDto userDto) throws AlreadyExistingResourceException {
        User user = convertToEntity(userDto);
        user.setAuthorities(CommonUtils.getAuthoritiesList(Authorities.ROLE_USER.toString()));
        User userCreated = null;
        if (user != null) {
            userCreated = userService.addUser(user);
        }
        return convertToDto(userCreated);
    }



    @RequestMapping(value = "/resetPassword",
            method = RequestMethod.POST)
    public String resetPassword(@RequestBody UserDto userDto) throws ResourceNotFoundException, ParseException {
        return userService.resetPassword(userDto.getUsername(), userDto.getPassword());
    }

    @RequestMapping(value = "/changePasswordWithOTP/{otp}",
            method = RequestMethod.POST,
            produces = "application/json")
    public String changePasswordWithOTP(@PathVariable String otp,
                                        @RequestBody UserDto userDto)
            throws ResourceNotFoundException, ParseException {
        return userService.changePasswordWithOTP(userDto.getUsername(), userDto.getPassword(), otp);
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
