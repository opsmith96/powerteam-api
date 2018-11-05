package com.springbootorm.api.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    // Create
    public void addPerson(Person person) {
        personRepository.save(person);
    }

    // Read all
    public List<Person> getAllPersons(String active_flag) {
        List<Person> allPersons = new ArrayList<>();
        List<Person> activePersons = new ArrayList<>();
        List<Person> inActivePersons = new ArrayList<>();

        personRepository.findAll().forEach(allPersons::add);
        for (Person person : allPersons) {
            if (person.getActivity()) {
                activePersons.add(person);
            } else {
                inActivePersons.add(person);
            }
        }
        if (active_flag.equals("active")) {
            return activePersons;
        } else if (active_flag.equals("inactive")) {
            return inActivePersons;
        } else {
            return allPersons;
        }
    }

    // Read one
    public ArrayList<Person> getPerson(Integer id) {
        ArrayList<Person> personList = new ArrayList<>();
        personList.add(personRepository.findById(id).get());
        return personList;
    }

    // Update
    public void updatePerson(Person person, Integer id) {
        person.setPerson_id(id);
        Person dbPerson = personRepository.findById(id).get();
        if (person.getFirst_name() != null && !person.getFirst_name().equals("")) {
            dbPerson.setFirst_name(person.getFirst_name());
        }
        if (person.getLast_name() != null && !person.getLast_name().equals("")) {
            dbPerson.setLast_name(person.getLast_name());
        }
        if (person.getAddress_id() != null && !person.getAddress_id().equals("")) {
            dbPerson.setAddress_id(person.getAddress_id());
        }
        if (person.getDate_of_birth() != null && !person.getDate_of_birth().equals("")) {
            dbPerson.setDate_of_birth(person.getDate_of_birth());
        }

        personRepository.save(dbPerson);
    }

    // Delete
    public void deletePerson(Integer id) {
        Person personToDelete = personRepository.findById(id).get();
        personToDelete.setActivity(false);
        updatePerson(personToDelete, id);
    }

    // Check if exists
    public boolean checkIfExists(Integer id) {
        return personRepository.existsById(id);
    }

    // Search
    public List<Person> getPersonByWord(String word) {
        List<Person> persons = new ArrayList<>();
        List<Person> wordPersons = new ArrayList<>();

        personRepository.findAll().forEach(persons::add);

        for (Person person : persons) {
            if (person.getFirst_name().contains(word)) {
                wordPersons.add(person);
            } else if (person.getLast_name().contains(word)) {
                wordPersons.add(person);
            }
        }
        return wordPersons;
    }

}