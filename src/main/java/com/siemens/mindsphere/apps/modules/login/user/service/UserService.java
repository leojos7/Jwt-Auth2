package com.siemens.mindsphere.apps.modules.login.user.service;

import com.siemens.mindsphere.apps.modules.login.user.entity.User;
import com.siemens.mindsphere.apps.modules.login.exception.AlreadyExistingUserException;
import com.siemens.mindsphere.apps.modules.login.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    public User addUser(User user, String authorityName) throws AlreadyExistingUserException;

    public void deleteUser(Integer userId) throws UserNotFoundException;

    public User updateUser(User user) throws UserNotFoundException;

    public Page<User> getAllUsers(Pageable pageable);

    public User getUserByUsername(String username) throws UserNotFoundException;

    public User getUserById(Integer id) throws UserNotFoundException;
}
