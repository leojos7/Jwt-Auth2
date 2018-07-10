package com.siemens.mindsphere.apps.modules.login.controller;

import com.siemens.mindsphere.apps.modules.login.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure/sales")
public class SalesController {

    @Autowired
    public UserService userService;


}
