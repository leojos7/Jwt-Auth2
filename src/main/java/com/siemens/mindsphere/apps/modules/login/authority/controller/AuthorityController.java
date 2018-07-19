package com.siemens.mindsphere.apps.modules.login.authority.controller;

import com.siemens.mindsphere.apps.modules.login.authority.dto.AuthorityDto;
import com.siemens.mindsphere.apps.modules.login.authority.entity.Authority;
import com.siemens.mindsphere.apps.modules.login.authority.service.AuthorityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure/admin/authority")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AuthorityDto addAuthority(@RequestBody AuthorityDto authorityDto) {
        Authority authority = convertToEntity(authorityDto);
        Authority authorityCreated = null;
        if (authority != null) {
            authorityCreated = authorityService.addAuthority(authority);
        }
        return convertToDto(authorityCreated);
    }

    @RequestMapping(value = "/delete/{authorityId}", method = RequestMethod.GET)
    public void deleteAuthority(@PathVariable int authorityId) {
        authorityService.deleteAuthority(authorityId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public AuthorityDto updateAuthority(@RequestBody AuthorityDto authorityDto) {
        Authority authority = convertToEntity(authorityDto);
        Authority authorityUpdated = null;
        if (authority != null) {
            authorityUpdated = authorityService.updateAuthority(authority);
        }
        return convertToDto(authorityUpdated);
    }
    @RequestMapping(value = "/get/{authorityId}", method = RequestMethod.GET)
    public AuthorityDto getAuthority(@PathVariable int authorityId) {
        return convertToDto(authorityService.getAuthority(authorityId));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Page<AuthorityDto> getAllAuthorities(Pageable pageable) {
        return convertToDtos(authorityService.getAllAuthorities(pageable));
    }

    private Page<AuthorityDto> convertToDtos(Page<Authority> authorities) {
        return authorities.map(authorityToMap -> convertToDto(authorityToMap));
    }

    private AuthorityDto convertToDto(Authority authority) {
        AuthorityDto authorityDto = null;
        if (authority != null) {
            authorityDto = modelMapper.map(authority, AuthorityDto.class);
        }
        return authorityDto;
    }

    private Authority convertToEntity(AuthorityDto authorityDto) {
        Authority authority = null;
        if (authorityDto != null) {
            authority = modelMapper.map(authorityDto, Authority.class);
        } else {

        }
        return authority;
    }

}
