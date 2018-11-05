package com.springbootorm.api.player;

import javax.persistence.*;

@Entity
public class Player {

    @Id
    private Integer player_id;

    private Boolean active_flag;
    private Integer person_id;
    private Integer team_id;
    private String normal_position;
    private String number;

    public Player(){};

    public Player(Integer player_id,Integer person_id, Integer team_id, String normal_position, String number, boolean active_flag) {
        this.player_id = player_id;
        this.active_flag = active_flag;
        this.person_id = person_id;
        this.team_id = team_id;
        this.normal_position = normal_position;
        this.number = number;
    }

    public Integer getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Integer player_id) {
        this.player_id = player_id;
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

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public String getNormal_position() {
        return normal_position;
    }

    public void setNormal_position(String normal_position) {
        this.normal_position = normal_position;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
