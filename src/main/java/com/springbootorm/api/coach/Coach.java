package com.springbootorm.api.coach;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Coach {

    @Id
    private Integer coach_id;

    private Boolean active_flag;
    private Integer person_id;

    public Coach(){}

    public Coach(Integer coach_id, Integer person_id, boolean active_flag) {
        this.coach_id = coach_id;
        this.person_id = person_id;
        this.active_flag = active_flag;
    }

    public Integer getCoach_id() {
        return coach_id;
    }

    public void setCoach_id(Integer coach_id) {
        this.coach_id = coach_id;
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
