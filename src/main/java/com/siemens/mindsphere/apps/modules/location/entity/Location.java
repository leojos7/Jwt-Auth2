package com.siemens.mindsphere.apps.modules.location.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "LOCATION")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "LOCATION_PARAM_MAP",
            joinColumns = {@JoinColumn(name = "LOCATION_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "LOCATION_PARAM_ID", referencedColumnName = "id")})
    private Set<LocationParams> locationParams;

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

    public Set<LocationParams> getLocationParams() {
        return locationParams;
    }

    public void setLocationParams(Set<LocationParams> locationParams) {
        this.locationParams = locationParams;
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
