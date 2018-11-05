package com.springbootorm.api.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    //Request: Create
    @RequestMapping(method = RequestMethod.POST, value = "/contacts")
    public void createContact(@RequestBody Contact contact){
        int i = 0;
        System.out.println("checkIfExists: " + contactService.checkIfExists(i));
        while(contactService.checkIfExists(i)){
            i++;
            System.out.println(i);
            System.out.println(contactService.checkIfExists(i));
        }
        contact.setContact_id(i);
        contactService.addContact(contact);
    }

    //Request: Read all
    @RequestMapping("/contacts")
    public List<Contact> getAllContacts(){
        return contactService.getAllContacts("all");
    }

    //Request: Read one by id
    @RequestMapping("/contacts/{id}")
    public ArrayList<Contact> readContact(@PathVariable Integer id){
        return contactService.getContact(id);
    }

    //Request: Update
    @RequestMapping(method = RequestMethod.PUT, value = "/contacts/{id}")
    public void updateContact(@RequestBody Contact contact, @PathVariable Integer id){
        contactService.updateContact(contact,id);
    }

    //Request: Delete by id
    @RequestMapping(method = RequestMethod.DELETE, value = "/contacts/{id}")
    public void deleteContact(@PathVariable Integer id){
        contactService.deleteContact(id);
    }
}
