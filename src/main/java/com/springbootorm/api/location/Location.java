package com.springbootorm.api.location;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Location {

    @Id
    private Integer location_id;

    private Boolean active_flag;
    private Integer address_id;
    private String name;
    private String description;

    public Location(){}

    public Location(Integer location_id, Integer address_id, String name ,String description, boolean active_flag){
        this.location_id = location_id;
        this.active_flag = active_flag;
        this.address_id = address_id;
        this.name = name;
        this.description = description;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public boolean getActivity() {
        return active_flag;
    }

    public void setActivity(Boolean active_flag) {
        this.active_flag = active_flag;
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
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
