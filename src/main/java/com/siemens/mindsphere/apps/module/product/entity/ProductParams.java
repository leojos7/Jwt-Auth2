package com.siemens.mindsphere.apps.module.product.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "PRODUCT_PARAMS")
public class ProductParams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

/*    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private Set<ParamDetails> paramDetails;*/

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

/*    public Set<ParamDetails> getParamDetails() {
        return paramDetails;
    }

    public void setParamDetails(Set<ParamDetails> paramDetails) {
        this.paramDetails = paramDetails;
    }*/
}
