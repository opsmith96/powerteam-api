package com.springbootorm.api.match;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
public class Match {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer match_id;

    private Boolean active_flag;
    private LocalDate match_date;
    private Integer home_team_id;
    private Integer away_team_id;
    private Integer season_id;
    private Integer location_id;

    public Match() {

    }
    public Match(Integer match_id, LocalDate match_date, Integer home_team_id, Integer away_team_id, Integer season_id, Integer location_id, boolean active_flag) {
        this.match_id = match_id;
        this.active_flag = active_flag;
        this.match_date = match_date;
        this.home_team_id = home_team_id;
        this.away_team_id = away_team_id;
        this.season_id = season_id;
        this.location_id = location_id;
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

    public LocalDate getMatch_date() {
        return match_date;
    }

    public void setMatch_date(LocalDate matchLocalDate) {
        this.match_date = matchLocalDate;
    }

    public Integer getHome_team_id() {
        return home_team_id;
    }

    public void setHome_team_i(Integer home_team_id) {
        this.home_team_id = home_team_id;
    }

    public Integer getAway_team_id() {
        return away_team_id;
    }

    public void setAway_team_id(Integer away_team_id) {
        this.away_team_id = away_team_id;
    }

    public Integer getSeason_id() {
        return season_id;
    }

    public void setSeason_id(Integer season_id) {
        this.season_id = season_id;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

}
