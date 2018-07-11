package com.siemens.mindsphere.apps.modules.login.userParams.service;

import com.siemens.mindsphere.apps.modules.login.userParams.entity.UserParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserParamsService {

    UserParams addUserParams(UserParams userParams);

    void deleteUserParams(Integer userParamsId);

    UserParams updateUserParams(UserParams userParams);

    UserParams getUserParams(Integer userParamsId);

    Page<UserParams> getAllUserParams(Pageable pageable);
}
