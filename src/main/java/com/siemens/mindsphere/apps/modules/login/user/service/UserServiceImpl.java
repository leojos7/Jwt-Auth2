package com.siemens.mindsphere.apps.modules.login.user.service;

import com.siemens.mindsphere.apps.exception.ErrorMappings;
import com.siemens.mindsphere.apps.modules.login.user.entity.User;
import com.siemens.mindsphere.apps.modules.login.exception.AlreadyExistingUserException;
import com.siemens.mindsphere.apps.modules.login.exception.UserNotFoundException;
import com.siemens.mindsphere.apps.modules.login.user.repository.UserRepository;
import com.siemens.mindsphere.apps.modules.login.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user, String authorityName) throws AlreadyExistingUserException {
        User addedUser = null;
        if (userRepository.findByUsernameCaseInsensitive(user.getUsername()) == null) {
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
    public void deleteUser(Integer userId) throws UserNotFoundException {
        User user = getUserById(userId);
        userRepository.delete(user);
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        User oldUser = getUserById(user.getUserId());
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
    public User getUserByUsername(String username) throws UserNotFoundException {

        User existingUser = userRepository.findByUsernameCaseInsensitive(username);
        if(existingUser == null) {
            throw new UserNotFoundException(ErrorMappings.USER_NOT_FOUND_CODE, ErrorMappings.USER_NOT_FOUND_MESSAGE);
        }
        return existingUser;
    }

    @Override
    public User getUserById(Integer id) throws UserNotFoundException{
        Optional<User> userOptional = userRepository.findById(id);
        User existingUser = null;
        if(userOptional.isPresent()) {
            existingUser = userOptional.get();
        } else {
            throw new UserNotFoundException(ErrorMappings.USER_NOT_FOUND_CODE, ErrorMappings.USER_NOT_FOUND_MESSAGE);
        }
        return existingUser;
    }

}
