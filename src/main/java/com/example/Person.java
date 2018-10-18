package com.example;

public class Person {
    String person_id;
    String first_name;
    String last_name;
    String date_of_birth;
    String address_id;

    public Person(String person_id, String first_name, String last_name, String date_of_birth, String address_id) {
        this.person_id = person_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.address_id = address_id;
    }

    public String getPersonID() {
        return person_id;
    }
    public String getPersonFirstName() {
        return first_name;
    }
    public String getPersonLastName() {
        return last_name;
    }
    public String getPersonDateOfBirth() {
        return date_of_birth;
    }
    public String getPersonAdressId() {
        return address_id;
    }

}
