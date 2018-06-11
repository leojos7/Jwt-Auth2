package com.siemens.mindsphere.apps.module.login.service;

import com.siemens.mindsphere.apps.module.login.entity.User;

import java.util.List;

public interface UserService {

    public void addUser(User user);

    public void deleteUser(String username);

    public User updateUser(User user);

    public List<User> getAllUsers();

    public User getUser(String username);
}
