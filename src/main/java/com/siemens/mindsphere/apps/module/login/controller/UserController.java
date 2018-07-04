package com.siemens.mindsphere.apps.module.login.controller;

import com.siemens.mindsphere.apps.module.login.entity.User;
import com.siemens.mindsphere.apps.module.login.exception.NoUserFoundException;
import com.siemens.mindsphere.apps.module.login.exception.ParseException;
import com.siemens.mindsphere.apps.module.login.service.UserService;
import com.siemens.mindsphere.apps.module.login.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure/user")
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    public String sayHello() {
        return "Secure Hello!";
    }

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteEmployee(@RequestHeader("Authorization") String authorization)
            throws NoUserFoundException, ParseException {
        userService.deleteUser(CommonUtils.getUsernameFromAccessToken(authorization));
        return "Deleted user";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public User updateUser(@RequestHeader("Authorization") String authorization, @RequestBody User user)
            throws NoUserFoundException {
        String username = CommonUtils.getUsernameFromAccessToken(authorization);
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
    public User getUser(@RequestHeader("Authorization") String authorization)
            throws NoUserFoundException, ParseException {
        String username = CommonUtils.getUsernameFromAccessToken(authorization);
        User user = userService.getUser(username);
        if (user == null) {
            throw new NoUserFoundException(username + " doesn't exist");
        }
        return user;
    }

    @RequestMapping(value = "/get/{username}", method = RequestMethod.GET, produces = "application/json")
    public User getUserByNAme(@PathVariable String username)
            throws NoUserFoundException, ParseException {
        User user = userService.getUser(username);
        if (user == null) {
            throw new NoUserFoundException(username + " doesn't exist");
        }
        return user;
    }

}
