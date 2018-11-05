package com.springbootorm.api.owner;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Owner {

    @Id
    private Integer owner_id;

    private Boolean active_flag;
    private Integer person_id;

    public Owner(){}

    public Owner(Integer owner_id, Integer person_id, boolean active_flag) {
        this.owner_id = owner_id;
        this.active_flag = active_flag;
        this.person_id = person_id;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public boolean getActivity() {
        return active_flag;
    }

    public void setActivity(Boolean active_flag) {
        this.active_flag = active_flag;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }
}
