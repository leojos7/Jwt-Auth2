package com.siemens.mindsphere.apps.module.login.controller;

import com.siemens.mindsphere.apps.module.login.entity.User;
import com.siemens.mindsphere.apps.module.login.exception.AlreadyExistingUserException;
import com.siemens.mindsphere.apps.module.login.exception.NoUserFoundException;
import com.siemens.mindsphere.apps.module.login.service.UserService;
import com.siemens.mindsphere.apps.module.login.utils.Authorities;
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

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public User signUp(@RequestBody User user) throws AlreadyExistingUserException, NoUserFoundException {
        return userService.addUser(user, Authorities.ROLE_USER.toString());
    }

    @RequestMapping(value = "/sales/sign-up", method = RequestMethod.POST)
    public User salesSignUp(@RequestBody User user) throws AlreadyExistingUserException, NoUserFoundException {
        return userService.addUser(user, Authorities.ROLE_SALES.toString());
    }
}
