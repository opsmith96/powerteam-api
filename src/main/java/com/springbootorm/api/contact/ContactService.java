package com.springbootorm.api.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    //Create
    public void addContact(Contact contact){
        contactRepository.save(contact);
    }

    //Read all
    public List<Contact> getAllContacts(String active_flag){
        List<Contact> allContacts = new ArrayList<>();
        List<Contact> activeContacts = new ArrayList<>();
        List<Contact> inActiveContacts = new ArrayList<>();

        contactRepository.findAll().forEach(allContacts::add);
        for (Contact contact:allContacts) {
            if(contact.getActivity()){
                activeContacts.add(contact);
            }else{
                inActiveContacts.add(contact);
            }
        }
        if(active_flag.equals("active")){
            return activeContacts;
        }else if(active_flag.equals("inactive")){
            return inActiveContacts;
        }else {
            return allContacts;
        }
    }

    //Read one
    public ArrayList<Contact> getContact(Integer id){
        ArrayList<Contact> contactList = new ArrayList<>();
        contactList.add(contactRepository.findById(id).get());
        return contactList;
    }

    //Update
    public void updateContact(Contact contact, Integer id) {
        Contact dbContact = contactRepository.findById(id).get();
        if(contact.getPerson_id() != null){
            dbContact.setPerson_id(contact.getPerson_id());
        }
        if(contact.getContact_type() != null && !contact.getContact_type().equals("")){
            dbContact.setContact_type(contact.getContact_type());
        }
        if(contact.getContact_detail() != null && !contact.getContact_detail().equals("")){
            dbContact.setContact_detail(contact.getContact_detail());
        }
        if(contact.getActivity() || !contact.getActivity()){
            dbContact.setActivity(contact.getActivity());
        }
        contactRepository.save(dbContact);
        }

    //Delete
    public void deleteContact(Integer id) {
        Contact contactToDelete = contactRepository.findById(id).get();
        contactToDelete.setActivity(false);
        updateContact(contactToDelete,id);
    }

    //Check if exists
    public boolean checkIfExists(Integer id){
        return contactRepository.existsById(id);
    }
    
}
