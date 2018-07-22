package com.siemens.mindsphere.apps.modules.login.user.service;

import com.siemens.mindsphere.apps.common.email.EmailService;
import com.siemens.mindsphere.apps.common.exception.AlreadyExistingResourceException;
import com.siemens.mindsphere.apps.common.exception.MailNotSentException;
import com.siemens.mindsphere.apps.common.exception.ResourceNotFoundException;
import com.siemens.mindsphere.apps.common.exception.TokenExpiredException;
import com.siemens.mindsphere.apps.common.utils.CommonUtils;
import com.siemens.mindsphere.apps.exceptionHandler.ErrorMappings;
import com.siemens.mindsphere.apps.modules.login.user.entity.User;
import com.siemens.mindsphere.apps.modules.login.user.repository.UserRepository;
import com.siemens.mindsphere.apps.modules.login.userParams.service.UserParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static com.siemens.mindsphere.apps.common.utils.Constants.*;
import static com.siemens.mindsphere.apps.exceptionHandler.ErrorMappings.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserParamsService userParamsService;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CommonUtils commonUtils;

    @Override
    public User addUser(User user) throws AlreadyExistingResourceException {
        User addedUser = null;
        if (userRepository.findByUsernameCaseInsensitive(user.getUsername()) == null) {
            if (user.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            user.setStatus(Boolean.FALSE);
            if (user.getUserParams() != null) {
                user.setUserParams(user.getUserParams().stream()
                        .filter(Objects::nonNull)
                        .map(userParams -> {
                            if (userParams.getUserParamId() != null) {
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
    public String deleteUser(Integer userId) throws ResourceNotFoundException {
        User user = getUserById(userId);
        userRepository.delete(user);
        return USER_DELETED;
    }

    @Override
    public User updateUser(User user) throws ResourceNotFoundException {
        User existingUser = getUserById(user.getUserId());
        User newUser = null;
        if (existingUser != null) {
            existingUser.setOtp(user.getOtp());
            existingUser.setFullName(user.getFullName());
            existingUser.setModifiedDate(new Date());
            existingUser.setMobileNumber(user.getMobileNumber());
            if (!CollectionUtils.isEmpty(user.getUserParams())) {
                user.getUserParams().stream()
                        .forEach(userParams -> userParamsService.updateUserParams(userParams));
            }
            newUser = userRepository.save(existingUser);
        } else {
            newUser = userRepository.save(newUser);
        }
        return newUser;
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserByUsername(String username) throws ResourceNotFoundException {

        User existingUser = userRepository.findByUsernameCaseInsensitive(username);
        if (existingUser == null) {
            throw new ResourceNotFoundException(ErrorMappings.USER_NOT_FOUND_CODE, ErrorMappings.USER_NOT_FOUND_MESSAGE);
        }
        return existingUser;
    }

    @Override
    public User getUserById(Integer id) throws ResourceNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        User existingUser = null;
        if (userOptional.isPresent()) {
            existingUser = userOptional.get();
        } else {
            throw new ResourceNotFoundException(ErrorMappings.USER_NOT_FOUND_CODE, ErrorMappings.USER_NOT_FOUND_MESSAGE);
        }
        return existingUser;
    }

    @Override
    public String sentOtp(String username) throws ResourceNotFoundException {
        String otp = commonUtils.generateOTP();
        User existingUser = getUserByUsername(username);
        existingUser.setOtp(otp);
        updateUser(existingUser);
        return OTP_GENERATED;
    }

    @Override
    public String changePasswordWithOTP(String username, String newPassword, String otp)
            throws ResourceNotFoundException {
        User existingUser = getUserByUsername(username);
        String responseMessage = null;
        if (existingUser.getOtp().equalsIgnoreCase(otp)) {
            existingUser.setPassword(passwordEncoder.encode(newPassword));
            existingUser.setModifiedDate(new Date());
            userRepository.save(existingUser);
            responseMessage = PASSWORD_RESET_SUCCESS;
        } else {
            responseMessage = ENTER_CORRECT_OTP;
        }
        return responseMessage;
    }

    @Override
    public String resetPassword(String username, String newPassword)
            throws ResourceNotFoundException, TokenExpiredException {
        User existingUser = getUserByUsername(commonUtils.parseJWT(username));
        existingUser.setPassword(passwordEncoder.encode(newPassword));
        existingUser.setStatus(Boolean.TRUE);
        existingUser.setModifiedDate(new Date());
        userRepository.save(existingUser);
        return PASSWORD_RESET_SUCCESS;
    }

    @Override
    public User updateUserRole(User user) throws ResourceNotFoundException {
        User existingUser = getUserById(user.getUserId());
        User newUser = null;
        if (existingUser != null) {
            existingUser.setOtp(user.getOtp());
            existingUser.setFullName(user.getFullName());
            existingUser.setModifiedDate(new Date());
            existingUser.setMobileNumber(user.getMobileNumber());
            existingUser.setAuthorities(user.getAuthorities());
            existingUser.setPassword(user.getPassword());
            if (!CollectionUtils.isEmpty(user.getUserParams())) {
                user.getUserParams().stream()
                        .forEach(userParams -> userParamsService.updateUserParams(userParams));
            }
            newUser = userRepository.save(existingUser);
        } else {
            newUser = userRepository.save(newUser);
        }
        return newUser;
    }

    @Override
    public String activateUser(String username, Boolean status) throws ResourceNotFoundException {
        User existingUser = getUserByUsername(username);
        if (existingUser != null) {
            existingUser.setStatus(status);
            userRepository.save(existingUser);
        }
        return USER_ACTIVATED;
    }

    @Override
    public String forgotPassword(String username) throws ResourceNotFoundException, MailNotSentException {
        User existingUser = getUserByUsername(username);
        commonUtils.sendPasswordSettingNotificationEmail(existingUser);
        return FORGOT_PASSWORD_MAIL_SENT;
    }

}


