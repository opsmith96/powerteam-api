package com.springbootorm.api.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    //Create
    public void addOwner(Owner owner){
        ownerRepository.save(owner);
    }

    //Read all
    public List<Owner> getAllOwners(String active_flag){
        List<Owner> allOwners = new ArrayList<>();
        List<Owner> activeOwners = new ArrayList<>();
        List<Owner> inActiveOwners = new ArrayList<>();

        ownerRepository.findAll().forEach(allOwners::add);
        for (Owner owner:allOwners) {
            if(owner.getActivity()){
                activeOwners.add(owner);
            }else{
                inActiveOwners.add(owner);
            }
        }
        if(active_flag.equals("active")){
            return activeOwners;
        }else if(active_flag.equals("inactive")){
            return inActiveOwners;
        }else {
            return allOwners;
        }
    }

    //Read one
    public ArrayList<Owner> getOwner(Integer id){
        ArrayList<Owner> ownerList = new ArrayList<>();
        ownerList.add(ownerRepository.findById(id).get());
        return ownerList;
    }

    //Update
    public void updateOwner(Owner owner, Integer id) {
        owner.setOwner_id(id);
        Owner dbOwner = ownerRepository.findById(id).get();
        if(owner.getPerson_id() != null && !owner.getPerson_id().equals("")){
            dbOwner.setPerson_id(owner.getPerson_id());
        }
        ownerRepository.save(dbOwner);
    }

    //Delete
    public void deleteOwner(Integer id) {
        Owner ownerToDelete = ownerRepository.findById(id).get();
        ownerToDelete.setActivity(false);
        updateOwner(ownerToDelete,id);
    }

    //Check if exists
    public boolean checkIfExists(Integer id){
        return ownerRepository.existsById(id);
    }
    
}
