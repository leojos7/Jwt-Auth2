package com.siemens.mindsphere.apps.module.login.service;

import com.siemens.mindsphere.apps.module.login.entity.User;
import com.siemens.mindsphere.apps.module.login.exception.AlreadyExistingUserException;
import com.siemens.mindsphere.apps.module.login.exception.NoUserFoundException;
import com.siemens.mindsphere.apps.module.login.security.repository.UserRepository;
import com.siemens.mindsphere.apps.module.login.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user, String authorityName) throws NoUserFoundException, AlreadyExistingUserException {
        User addedUser = null;
        if (getUserByUsername(user.getUsername()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setStatus(Boolean.TRUE);
            user.setAuthorities(CommonUtils.getAuthoritiesList(authorityName));
            userRepository.save(user);
        } else {
            throw new AlreadyExistingUserException(user.getUsername() + " is already existing user.");
        }
        return addedUser;
    }

    @Override
    public void deleteUser(Integer userId) throws NoUserFoundException {
        User user = getUserById(userId);
        if (user == null) {
            throw new NoUserFoundException(userId + " doesn't exist");
        }
        userRepository.delete(user);
    }

    @Override
    public User updateUser(User user) throws NoUserFoundException {
        User oldUser = getUserById(user.getId());
        if (oldUser == null) {
            throw new NoUserFoundException(user.getUsername() + " doesn't exist");
        }
        oldUser.setModifiedDate(new Date());
/*        oldUser.setFullName(user.getFullName());
        oldUser.setMobileNumber(user.getMobileNumber());*/
        return userRepository.save(oldUser);
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserByUsername(String username) {
        User existingUser = userRepository.findByUsernameCaseInsensitive(username);
        return existingUser;
    }

    @Override
    public User getUserById(Integer id) {
        User existingUser = userRepository.findById(id).get();
        return existingUser;
    }

}
