package com.siemens.mindsphere.apps.modules.login.authority.service;

import com.siemens.mindsphere.apps.modules.login.authority.entity.Authority;
import com.siemens.mindsphere.apps.modules.login.authority.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority addAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public void deleteAuthority(Integer authorityId) {
        Optional<Authority> authorityOptional = authorityRepository.findById(authorityId);
        if (authorityOptional.isPresent()) {
            authorityRepository.delete(authorityOptional.get());
        }
    }

    @Override
    public Authority updateAuthority(Authority authority) {
        Optional<Authority> authorityOptional = authorityRepository.findById(authority.getAuthorityId());
        Authority existingAuthority = null;
        Authority newAuthority = null;
        if (authorityOptional.isPresent()) {
            existingAuthority = authorityOptional.get();
            existingAuthority.setName(authority.getName());
            existingAuthority.setModifiedDate(new Date());
            newAuthority = authorityRepository.save(existingAuthority);
        } else {
            newAuthority = authorityRepository.save(authority);
        }
        return newAuthority;
    }

    @Override
    public Authority getAuthority(Integer authorityId) {
        Authority authority = null;
        if(authorityRepository.findById(authorityId).isPresent()) {
            authority = authorityRepository.findById(authorityId).get();
        }
        return authority;
    }

    @Override
    public Page<Authority> getAllAuthorities(Pageable pageable) {
        return authorityRepository.findAll(pageable);
    }

}
