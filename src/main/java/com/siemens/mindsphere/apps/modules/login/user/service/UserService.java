package com.siemens.mindsphere.apps.modules.login.user.service;

import com.siemens.mindsphere.apps.common.exception.AlreadyExistingResourceException;
import com.siemens.mindsphere.apps.common.exception.MailNotSentException;
import com.siemens.mindsphere.apps.common.exception.ResourceNotFoundException;
import com.siemens.mindsphere.apps.common.exception.TokenExpiredException;
import com.siemens.mindsphere.apps.modules.login.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    public User addUser(User user) throws AlreadyExistingResourceException;

    public void deleteUser(Integer userId) throws ResourceNotFoundException;

    public User updateUser(User user) throws ResourceNotFoundException;

    public Page<User> getAllUsers(Pageable pageable);

    public User getUserByUsername(String username) throws ResourceNotFoundException;

    public User getUserById(Integer id) throws ResourceNotFoundException;

    public String sentOtp(String username) throws ResourceNotFoundException;

    public String changePasswordWithOTP(String username, String newPassword, String otp) throws ResourceNotFoundException;

    public String resetPassword(String username, String newPassword) throws ResourceNotFoundException, TokenExpiredException;

    public String updateUserRole(User user) throws ResourceNotFoundException;

    public String activateUser(String username, Boolean status) throws ResourceNotFoundException;

    public String forgotPassword(String username) throws ResourceNotFoundException, MailNotSentException;

    public void sendPasswordSettingNotificationEmail(User user) throws MailNotSentException;

}
