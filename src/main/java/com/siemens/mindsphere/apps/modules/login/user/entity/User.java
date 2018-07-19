package com.siemens.mindsphere.apps.modules.login.user.entity;

import com.siemens.mindsphere.apps.entity.BaseEntity;
import com.siemens.mindsphere.apps.modules.login.authority.entity.Authority;
import com.siemens.mindsphere.apps.modules.login.userParams.entity.UserParams;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer userId;

    @Column(updatable = false, nullable = false)
    @Size(min = 0, max = 50)
    private String username;

    @Size(min = 0, max = 500)
    private String password;

    @Size(min = 0, max = 500)
    private String fullName;

    private String mobileNumber;

    private boolean status;

    private String otp;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "USER_PARAM_MAP",
            joinColumns = {@JoinColumn(name = "LOGIN_ID", referencedColumnName = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_PARAM_ID", referencedColumnName = "USER_PARAM_ID")})
    private Set<UserParams> userParams;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "USER_AUTHORITY_MAP",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "AUTHORITY_ID")})
    private Set<Authority> authorities;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

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

    public Set<UserParams> getUserParams() {
        return userParams;
    }

    public void setUserParams(Set<UserParams> userParams) {
        this.userParams = userParams;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
