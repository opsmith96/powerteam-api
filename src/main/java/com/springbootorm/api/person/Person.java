package com.springbootorm.api.person;

import com.springbootorm.api.address.Address;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
public class Person {

    @Id
    private Integer person_id;

    private Boolean active_flag;
    private Integer address_id;
    private String first_name;

    private String last_name;
    private LocalDate date_of_birth;

    public Person(){}

    public Person(Integer person_id,Integer address_id, String first_name, String last_name, LocalDate date_of_birth, boolean active_flag) {
        this.person_id = person_id;
        this.active_flag = active_flag;
        //this.address_id = address_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public boolean getActivity() {
        return active_flag;
    }

    public void setActivity(Boolean active_flag) {
        this.active_flag = active_flag;
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
}
