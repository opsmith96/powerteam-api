package com.springbootorm.api.association;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Association {

    @Id
    private Integer association_id;

    private Boolean active_flag;
    private String name;
    private String description;

    public Association(){}

    public Association(Integer association_id, String name, String description, boolean active_flag) {
        this.association_id = association_id;
        this.name = name;
        this.description = description;
        this.active_flag = active_flag;
    }

    public Integer getAssociation_id() {
        return association_id;
    }

    public void setAssociation_id(Integer association_id) {
        this.association_id = association_id;
    }

    public boolean getActivity() {
        return active_flag;
    }

    public void setActivity(Boolean active_flag) {
        this.active_flag = active_flag;
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
