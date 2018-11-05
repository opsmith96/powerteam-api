package com.springbootorm.api.association;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
public class AssociationController {
    
    @Autowired
    private AssociationService associationService;

    //Request: Create
    @RequestMapping(method = RequestMethod.POST, value = "/associations")
    public void createAssociation(@RequestBody Association association){
        int i = 0;
        System.out.println("checkIfExists: " + associationService.checkIfExists(i));
        while(associationService.checkIfExists(i)){
            i++;
            System.out.println(i);
            System.out.println(associationService.checkIfExists(i));
        }
        association.setAssociation_id(i);
        associationService.addAssociation(association);
    }

    //Request: Read all
    @RequestMapping("/associations")
    public List<Association> getAllAssociations(){
        return associationService.getAllAssociations("all");
    }

    //Request: Read all
    @RequestMapping(method = RequestMethod.GET, value = "/associationes_filtered/{active_flag}")
    public List<Association> getAllAssociations(@PathVariable String active_flag){
        return associationService.getAllAssociations(active_flag);
    }

    //Request: Read one by id
    @RequestMapping("/associations/{id}")
    public ArrayList<Association> readAssociation(@PathVariable Integer id){
        return associationService.getAssociation(id);
    }

    //Request: Update
    @RequestMapping(method = RequestMethod.PUT, value = "/associations/{id}")
    public void updateAssociation(@RequestBody Association association, @PathVariable Integer id){
        associationService.updateAssociation(association,id);
    }

    //Request: Delete by id
    @RequestMapping(method = RequestMethod.DELETE, value = "/associations/{id}")
    public void deleteAssociation(@PathVariable Integer id){
        associationService.deleteAssociation(id);
    }
    
}

