package com.siemens.mindsphere.apps.module.login.controller;

import com.siemens.mindsphere.apps.module.login.entity.User;
import com.siemens.mindsphere.apps.module.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure/admin")
public class AdminController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
    public Page<User> getAllUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }
}
