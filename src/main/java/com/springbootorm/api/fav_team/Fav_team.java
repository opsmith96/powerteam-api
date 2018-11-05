package com.springbootorm.api.fav_team;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fav_team {

    @Id
    private Integer relation_id;

    private Boolean active_flag;
    private Integer user_id;
    private Integer team_id;

    public Fav_team(){}

    public Fav_team(Integer user_id, Integer team_id,Integer relation_id, boolean active_flag) {
        this.user_id = user_id;
        this.active_flag = active_flag;
        this.team_id = team_id;
        this.relation_id = relation_id;
    }

    public Integer getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(Integer relation_id) {
        this.relation_id = relation_id;
    }

    public boolean getActivity() {
        return active_flag;
    }

    public void setActivity(Boolean active_flag) {
        this.active_flag = active_flag;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }
}
