package com.siemens.mindsphere.apps.modules.login.user.controller;

import com.siemens.mindsphere.apps.common.dto.ResponseDto;
import com.siemens.mindsphere.apps.common.exception.MailNotSentException;
import com.siemens.mindsphere.apps.common.exception.TokenExpiredException;
import com.siemens.mindsphere.apps.exceptionHandler.ParseException;
import com.siemens.mindsphere.apps.common.exception.AlreadyExistingResourceException;
import com.siemens.mindsphere.apps.common.exception.ResourceNotFoundException;
import com.siemens.mindsphere.apps.modules.login.user.dto.UserDto;
import com.siemens.mindsphere.apps.modules.login.user.entity.User;
import com.siemens.mindsphere.apps.modules.login.user.service.UserService;
import com.siemens.mindsphere.apps.common.enums.Authorities;
import com.siemens.mindsphere.apps.common.utils.CommonUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    public UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommonUtils commonUtils;

    @RequestMapping(value = "/sign-up",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public UserDto signUp(@RequestBody UserDto userDto) throws AlreadyExistingResourceException, MailNotSentException {
        User user = convertToEntity(userDto);
        user.setAuthorities(commonUtils.getAuthoritiesList(Authorities.ROLE_USER.toString()));
        User userCreated = null;
        if (user != null) {
            userCreated = userService.addUser(user);
        }
        commonUtils.sendActivationEmail(userCreated);
        return convertToDto(userCreated);
    }

    @RequestMapping(value = "/forgotPassword",
            method = RequestMethod.POST)
    public ResponseDto forgotPassword(@RequestBody UserDto userDto) throws ResourceNotFoundException, ParseException, MailNotSentException {
        ResponseDto responseDto = new ResponseDto();
        if(userDto.getUsername() != null) {
            responseDto.setMessage(userService.forgotPassword(userDto.getUsername()));
        }
        return responseDto;
    }

    @RequestMapping(value = "/resetPassword",
            method = RequestMethod.POST)
    public ResponseDto resetPassword(@RequestBody UserDto userDto)
            throws ResourceNotFoundException, ParseException, TokenExpiredException {
        ResponseDto responseDto = new ResponseDto();
        if(userDto.getUsername() != null && userDto.getPassword() != null) {
            responseDto.setMessage(userService.resetPassword(userDto.getUsername(), userDto.getPassword()));
        }
        return responseDto;
    }

    @RequestMapping(value = "/changePasswordWithOTP/{otp}",
            method = RequestMethod.POST,
            produces = "application/json")
    public ResponseDto changePasswordWithOTP(@RequestBody UserDto userDto)
            throws ResourceNotFoundException, ParseException {
        ResponseDto responseDto = new ResponseDto();
        if(userDto.getUsername() != null && userDto.getPassword() != null && userDto.getOtp() != null) {
            responseDto.setMessage(
                    userService.changePasswordWithOTP(
                            userDto.getUsername(),
                            userDto.getPassword(),
                            userDto.getOtp()));
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
