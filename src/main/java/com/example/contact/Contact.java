package com.example.contact;

public class Contact {
    int person_id;
    String contact_type;
    String contact_detalil;

    public Contact(int person_id, String contact_type, String contact_detail){
        this.person_id = person_id;
        this.contact_type = contact_type;
        this.contact_detalil = contact_detail;
    }

    public int getPersonID() {
        return person_id;
    }

    public String getContactType() {
        return contact_type;
    }

    public String getContactDetail() {
        return contact_detalil;
    }
}
