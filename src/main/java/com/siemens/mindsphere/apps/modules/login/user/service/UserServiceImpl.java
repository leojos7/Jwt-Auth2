package com.siemens.mindsphere.apps.modules.login.user.service;

import com.siemens.mindsphere.apps.exception.ErrorMappings;
import com.siemens.mindsphere.apps.modules.login.user.entity.User;
import com.siemens.mindsphere.apps.modules.exception.AlreadyExistingResourceException;
import com.siemens.mindsphere.apps.modules.exception.ResourceNotFoundException;
import com.siemens.mindsphere.apps.modules.login.user.repository.UserRepository;
import com.siemens.mindsphere.apps.modules.login.userParams.entity.UserParams;
import com.siemens.mindsphere.apps.modules.login.userParams.service.UserParamsService;
import com.siemens.mindsphere.apps.modules.login.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.siemens.mindsphere.apps.exception.ErrorMappings.ALREADY_EXISTING_USER_CODE;
import static com.siemens.mindsphere.apps.exception.ErrorMappings.ALREADY_EXISTING_USER_MESSAGE;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserParamsService userParamsService;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user, String authorityName) throws AlreadyExistingResourceException {
        User addedUser = null;
        if (userRepository.findByUsernameCaseInsensitive(user.getUsername()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setStatus(Boolean.TRUE);
            user.setAuthorities(CommonUtils.getAuthoritiesList(authorityName));
            if (user.getUserParams() != null) {
                user.setUserParams(user.getUserParams().stream()
                        .filter(Objects::nonNull)
                        .map(userParams -> {
                            if(userParams.getUserParamId() != null) {
                                return userParamsService.getUserParams(userParams.getUserParamId());
                            } else {
                                return userParamsService.addUserParams(userParams);
                            }
                        })
                        .collect(Collectors.toSet()));
            }
            addedUser = userRepository.save(user);
        } else {
            throw new AlreadyExistingResourceException(ALREADY_EXISTING_USER_CODE, ALREADY_EXISTING_USER_MESSAGE);
        }
        return addedUser;
    }

    @Override
    public void deleteUser(Integer userId) throws ResourceNotFoundException {
        User user = getUserById(userId);
        userRepository.delete(user);
    }

    @Override
    public User updateUser(User user) throws ResourceNotFoundException {
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
    public User getUserByUsername(String username) throws ResourceNotFoundException {

        User existingUser = userRepository.findByUsernameCaseInsensitive(username);
        if(existingUser == null) {
            throw new ResourceNotFoundException(ErrorMappings.USER_NOT_FOUND_CODE, ErrorMappings.USER_NOT_FOUND_MESSAGE);
        }
        return existingUser;
    }

    @Override
    public User getUserById(Integer id) throws ResourceNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        User existingUser = null;
        if(userOptional.isPresent()) {
            existingUser = userOptional.get();
        } else {
            throw new ResourceNotFoundException(ErrorMappings.USER_NOT_FOUND_CODE, ErrorMappings.USER_NOT_FOUND_MESSAGE);
        }
        return existingUser;
    }

}
