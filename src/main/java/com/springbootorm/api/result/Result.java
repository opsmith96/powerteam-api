package com.springbootorm.api.result;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Result {

    @Id
    private Integer result_id;

    private Integer match_id;
    private Boolean active_flag;
    private Integer team_id;
    private String result;
    private Integer score;

    public Result(){}

    public Integer getResult_id() {
        return result_id;
    }

    public void setResult_id(Integer result_id) {
        this.result_id = result_id;
    }

    public Result(Integer match_id, Integer team_id, Integer score, boolean active_flag, String result, Integer result_id) {
        this.match_id = match_id;
        this.team_id = team_id;
        this.score = score;
        this.result = result;
        this.active_flag = active_flag;
        this.result_id = result_id;
    }

    public Integer getMatch_id() {
        return match_id;
    }

    public void setMatch_id(Integer match_id) {
        this.match_id = match_id;
    }

    public boolean getActivity() {
        return active_flag;
    }

    public void setActivity(Boolean active_flag) {
        this.active_flag = active_flag;
    }

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

}
