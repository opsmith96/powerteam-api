package com.springbootorm.api.season;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.time.LocalDate;

@Entity
public class Season {

    @Id
    private Integer season_id;

    private Boolean active_flag;
    private LocalDate start_date;
    private LocalDate end_date;
    private String name;
    private String description;

    public Season(){}

    public Season(Integer season_id, LocalDate start_date, LocalDate end_date, String name,String description, boolean active_flag){
        this.season_id = season_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.name = name;
        this.description = description;
        this.active_flag = active_flag;
    }


    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public boolean getActivity() {
        return active_flag;
    }

    public void setActivity(Boolean active_flag) {
        this.active_flag = active_flag;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
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
