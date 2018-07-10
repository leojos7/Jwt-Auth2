package com.siemens.mindsphere.apps.modules.product.entity;

import com.siemens.mindsphere.apps.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "PRODUCT_PARAMS")
public class ProductParams extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String description;

/*    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private Set<paramDetails> paramDetails;*/

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

/*    public Set<paramDetails> getParamDetails() {
        return paramDetails;
    }

    public void setParamDetails(Set<paramDetails> paramDetails) {
        this.paramDetails = paramDetails;
    }*/
}
