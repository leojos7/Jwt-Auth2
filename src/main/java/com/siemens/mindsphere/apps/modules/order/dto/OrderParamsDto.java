package com.siemens.mindsphere.apps.modules.order.dto;

public class OrderParamsDto {

    private Integer id;

    private String name;

    private String description;

    public OrderParamsDto() {
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

}
