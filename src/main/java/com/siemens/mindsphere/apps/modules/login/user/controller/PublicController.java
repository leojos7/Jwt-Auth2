package com.siemens.mindsphere.apps.modules.login.user.controller;

import com.siemens.mindsphere.apps.modules.login.user.dto.UserDto;
import com.siemens.mindsphere.apps.modules.login.user.entity.User;
import com.siemens.mindsphere.apps.modules.exception.AlreadyExistingResourceException;
import com.siemens.mindsphere.apps.modules.login.user.service.UserService;
import com.siemens.mindsphere.apps.modules.login.utils.Authorities;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    public UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public UserDto signUp(@RequestBody UserDto userDto) throws AlreadyExistingResourceException {
        User user = convertToEntity(userDto);
        User userCreated = null;
        if (user != null) {
            userCreated = userService.addUser(user, Authorities.ROLE_USER.toString());
        }
        return convertToDto(userCreated);
    }

    @RequestMapping(value = "/sales/sign-up", method = RequestMethod.POST)
    public UserDto salesSignUp(@RequestBody UserDto userDto) throws AlreadyExistingResourceException {
        User user = convertToEntity(userDto);
        User userCreated = null;
        if (user != null) {
            userCreated = userService.addUser(user, Authorities.ROLE_SALES.toString());
        }
        return convertToDto(userCreated);
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
