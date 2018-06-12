package com.siemens.mindsphere.apps.module.login.service;

import com.siemens.mindsphere.apps.module.login.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    public void addUser(User user);

    public void deleteUser(String username);

    public User updateUser(User user);

    public Page<User> getAllUsers(Pageable pageable);

    public User getUser(String username);
}
