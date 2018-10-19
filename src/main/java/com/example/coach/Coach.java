package com.example.coach;

public class Coach {
    int person_id;
    int coach_id;
    public Coach(int coach_id, int person_id) {
        this.coach_id = coach_id;
        this.person_id = person_id;
    }

    public int getPersonID() {
        return person_id;
    }

    public int getCoachID() {
        return coach_id;
    }
}
