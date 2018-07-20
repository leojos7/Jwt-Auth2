package com.siemens.mindsphere.apps.modules.login.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.siemens.mindsphere.apps.modules.login.authority.dto.AuthorityDto;
import com.siemens.mindsphere.apps.modules.login.userParams.dto.UserParamsDto;

import java.util.Date;
import java.util.Set;

public class UserDto {

    private Integer userId;

    private String username;

    private String password;

    private String fullName;

    private String mobileNumber;

    private boolean status;

    private String otp;

    private Set<AuthorityDto> authorities;

    private Set<UserParamsDto> userParams;

    private Date createDate;

    private Date modifiedDate;

    public UserDto() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Set<UserParamsDto> getUserParams() {
        return userParams;
    }

    public void setUserParams(Set<UserParamsDto> userParams) {
        this.userParams = userParams;
    }

    public Set<AuthorityDto> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityDto> authorities) {
        this.authorities = authorities;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
