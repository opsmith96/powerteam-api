package com.springbootorm.api.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.JsonPath;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    // Request: Create
    @RequestMapping(method = RequestMethod.POST, value = "/persons")
    public void createPerson(@RequestBody Person person) {
        int i = 0;
        System.out.println("checkIfExists: " + personService.checkIfExists(i));
        while (personService.checkIfExists(i)) {
            i++;
            System.out.println(i);
            System.out.println(personService.checkIfExists(i));
        }
        person.setPerson_id(i);
        personService.addPerson(person);
    }

    // Request: Read all
    @RequestMapping(method = RequestMethod.GET, value = "/persons_filtered/{active_flag}")
    public List<Person> getAllPersons(@PathVariable String active_flag) {
        return personService.getAllPersons(active_flag);
    }

    // Request: Read all
    @RequestMapping("/persons")
    public List<Person> getAllPersons() {
        return personService.getAllPersons("all");
    }

    // Request: Read one by id
    @RequestMapping("/persons/{id}")
    public ArrayList<Person> readPerson(@PathVariable Integer id) {
        return personService.getPerson(id);
    }

    // Request: Update
    @RequestMapping(method = RequestMethod.PUT, value = "/persons/{id}")
    public void updatePerson(@RequestBody Person person, @PathVariable Integer id) {
        personService.updatePerson(person, id);
    }

    // Request: Delete by id
    @RequestMapping(method = RequestMethod.DELETE, value = "/persons/{id}")
    public void deletePerson(@PathVariable Integer id) {
        personService.deletePerson(id);
    }

    // Search
    @RequestMapping("/search_persons/{word}")
    public List<Person> getPersonByWord(@PathVariable String word) {
        return personService.getPersonByWord(word);
    }
}
