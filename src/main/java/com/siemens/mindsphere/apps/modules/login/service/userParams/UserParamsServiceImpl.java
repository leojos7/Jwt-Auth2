package com.siemens.mindsphere.apps.modules.login.service.userParams;

import com.siemens.mindsphere.apps.modules.login.entity.UserParams;
import com.siemens.mindsphere.apps.modules.login.respository.UserParamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class UserParamsServiceImpl implements UserParamsService {

    @Autowired
    private UserParamsRepository userParamsRepository;

    @Override
    public UserParams addUserParams(UserParams userParams) {
        return userParamsRepository.save(userParams);
    }

    @Override
    public void deleteUserParams(Integer userParamsId) {
        Optional<UserParams> userParamsOptional = userParamsRepository.findById(userParamsId);
        if (userParamsOptional.isPresent()) {
            userParamsRepository.delete(userParamsOptional.get());
        }
    }

    @Override
    public UserParams updateUserParams(UserParams userParams) {
        Optional<UserParams> userParamsOptional = userParamsRepository.findById(userParams.getId());
        UserParams existingUserParams = null;
        UserParams newUserParams = null;
        if (userParamsOptional.isPresent()) {
            existingUserParams = userParamsOptional.get();
            existingUserParams.setName(userParams.getName());
            existingUserParams.setDescription(userParams.getDescription());
            existingUserParams.setModifiedDate(new Date());
            newUserParams = userParamsRepository.save(existingUserParams);
        } else {
            newUserParams = userParamsRepository.save(userParams);
        }
        return newUserParams;
    }

    @Override
    public UserParams getUserParams(Integer userParamsId) {
        UserParams userParams = null;
        if(userParamsRepository.findById(userParamsId).isPresent()) {
            userParams = userParamsRepository.findById(userParamsId).get();
        }
        return userParams;
    }

    @Override
    public Page<UserParams> getAllUserParams(Pageable pageable) {
        return userParamsRepository.findAll(pageable);
    }

}
