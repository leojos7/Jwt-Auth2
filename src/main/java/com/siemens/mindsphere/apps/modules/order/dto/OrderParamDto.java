package com.siemens.mindsphere.apps.modules.order.dto;

import java.util.Date;

public class OrderParamDto {

    private Integer id;

    private String name;

    private String description;

    private String value;

    private Date createDate;

    private Date modifiedDate;

    public OrderParamDto() {
    }

    public OrderParamDto(Integer id, String name, String description, String value) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
    }

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
