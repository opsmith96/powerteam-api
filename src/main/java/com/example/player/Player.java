package com.example.player;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Player {

    @Id
    private Integer person_id;
    private int team_id;
    private String normal_position;
    private String number;

    public Player(){};

    public Player(int person_id, int team_id, String normal_position, String number) {
        this.person_id = person_id;
        this.team_id = team_id;
        this.normal_position = normal_position;
        this.number = number;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
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
