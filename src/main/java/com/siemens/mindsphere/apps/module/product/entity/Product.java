package com.siemens.mindsphere.apps.module.product.entity;

import com.siemens.mindsphere.apps.module.login.entity.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDED_BY")
    private User addedBy;

    private String code;

    private Boolean status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "PRODUCT_PARAM_MAP",
            joinColumns = { @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "PARAM_DETAILS_ID", referencedColumnName = "id") })
    private Set<ParamDetails> paramDetails;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Set<ParamDetails> getParamDetails() {
        return paramDetails;
    }

    public void setParamDetails(Set<ParamDetails> paramDetails) {
        this.paramDetails = paramDetails;
    }
}
