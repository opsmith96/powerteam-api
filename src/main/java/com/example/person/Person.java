package com.example.person;

import java.sql.Date;

public class Person {
    int person_id;
    String first_name;
    String last_name;
    Date date_of_birth;
    int address_id;

    public Person(int person_id, String first_name, String last_name, Date date_of_birth, int address_id) {
        this.person_id = person_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.address_id = address_id;
    }

    public int getPersonID() {
        return person_id;
    }
    public String getPersonFirstName() {
        return first_name;
    }
    public String getPersonLastName() {
        return last_name;
    }
    public Date getPersonDateOfBirth() {
        return date_of_birth;
    }
    public int getPersonAdressId() {
        return address_id;
    }

}
