package com.siemens.mindsphere.apps.module.login.controller;

import com.siemens.mindsphere.apps.module.login.entity.User;
import com.siemens.mindsphere.apps.module.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/secure/admin")
public class AdminController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces="application/json")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
