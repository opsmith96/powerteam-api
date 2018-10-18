package com.example;

public class Person {
String person_id;
String first_name;
String last_name;
String date_of_birth;
String address_id;

    public Person(String person_id, String first_name, String last_name, String date_of_birth, String address_id){
       this.person_id =  person_id;
       this.first_name = first_name;
       this.last_name = last_name;
       this.date_of_birth= date_of_birth;
       this.address_id = address_id;
    }
    public String rPerson(){

        return person_id +" "+ first_name + last_name + date_of_birth + address_id;

    }
}
