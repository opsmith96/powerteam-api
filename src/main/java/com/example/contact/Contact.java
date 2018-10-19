package com.example.contact;

public class Contact {
    int person_id;
    int contact_id;
    String contact_type;
    String contact_detalil;

    public Contact(    int contact_id, int person_id, String contact_type, String contact_detail){
        this.contact_id=contact_id;
        this.person_id = person_id;
        this.contact_type = contact_type;
        this.contact_detalil = contact_detail;
    }

    public int getPersonID() {
        return person_id;
    }
    public int getContactID() {
        return contact_id;
    }

    public String getContactType() {
        return contact_type;
    }

    public String getContactDetail() {
        return contact_detalil;
    }
}
