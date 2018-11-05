package com.springbootorm.api.match_position;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Match_position {

    @Id
    private Integer player_id;

    private Boolean active_flag;
    private Integer match_id;
    private String position;

    public Match_position(){}

    public Match_position(Integer player_id, Integer match_id, String position, boolean active_flag) {
        this.player_id = player_id;
        this.match_id = match_id;
        this.position = position;
        this.active_flag = active_flag;
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

    public Integer getMatch_id() {
        return match_id;
    }

    public void setMatch_id(Integer match_id) {
        this.match_id = match_id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
