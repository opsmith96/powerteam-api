package com.example.owner;

public class Owner {
    int person_id;
    int owner_id;

    public Owner(int owner_id, int person_id) {
        this.owner_id = owner_id;
        this.person_id = person_id;
    }

    public int getPersonID() {
        return person_id;
    }

    public int getOwnerID() {
        return owner_id;
    }
}
