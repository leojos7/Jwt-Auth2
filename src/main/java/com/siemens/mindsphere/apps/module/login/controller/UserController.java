package com.siemens.mindsphere.apps.module.login.controller;

import com.siemens.mindsphere.apps.module.login.entity.User;
import com.siemens.mindsphere.apps.module.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secure/user")
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    public String sayHello() {
        return "Secure Hello!";
    }

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/delete/{name}", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable String username) {
        User user = new User();
        user.setUsername(username);
        user.setActivated(Boolean.FALSE);
        userService.updateUser(user);
        return "Deleted user";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/get/{name}", method = RequestMethod.GET, produces="application/json")
    public User getUser(@PathVariable String name) {
        return userService.getUser(name);
    }

}
