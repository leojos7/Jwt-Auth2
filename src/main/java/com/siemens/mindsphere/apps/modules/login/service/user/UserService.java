package com.siemens.mindsphere.apps.modules.login.service.user;

import com.siemens.mindsphere.apps.modules.login.entity.User;
import com.siemens.mindsphere.apps.modules.login.exception.AlreadyExistingUserException;
import com.siemens.mindsphere.apps.modules.login.exception.NoUserFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    public User addUser(User user, String authorityName) throws NoUserFoundException, AlreadyExistingUserException;

    public void deleteUser(Integer userId) throws NoUserFoundException;

    public User updateUser(User user) throws NoUserFoundException;

    public Page<User> getAllUsers(Pageable pageable);

    public User getUserByUsername(String username) throws NoUserFoundException;

    public User getUserById(Integer id) throws NoUserFoundException;
}
