package com.siemens.mindsphere.apps.modules.product.entity;

import com.siemens.mindsphere.apps.entity.BaseEntity;
import com.siemens.mindsphere.apps.modules.login.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "PRODUCT")
public class Product extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDED_BY")
    private User addedBy;

    private String code;

    private Boolean status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "PRODUCT_PARAM_MAP",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "PARAM_DETAILS_ID", referencedColumnName = "id")})
    private Set<ParamDetails> paramDetails;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Set<ParamDetails> getParamDetails() {
        return paramDetails;
    }

    public void setParamDetails(Set<ParamDetails> paramDetails) {
        this.paramDetails = paramDetails;
    }
}
