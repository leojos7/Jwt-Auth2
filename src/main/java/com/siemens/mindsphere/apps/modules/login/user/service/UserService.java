package com.siemens.mindsphere.apps.modules.login.user.service;

import com.siemens.mindsphere.apps.modules.login.user.entity.User;
import com.siemens.mindsphere.apps.modules.exception.AlreadyExistingResourceException;
import com.siemens.mindsphere.apps.modules.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    public User addUser(User user, String authorityName) throws AlreadyExistingResourceException;

    public void deleteUser(Integer userId) throws ResourceNotFoundException;

    public User updateUser(User user) throws ResourceNotFoundException;

    public Page<User> getAllUsers(Pageable pageable);

    public User getUserByUsername(String username) throws ResourceNotFoundException;

    public User getUserById(Integer id) throws ResourceNotFoundException;
}
