package com.springbootorm.api.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    //Request: Create
    @RequestMapping(method = RequestMethod.POST, value = "/owners")
    public void createOwner(@RequestBody Owner owner){
        int i = 0;
        System.out.println("checkIfExists: " + ownerService.checkIfExists(i));
        while(ownerService.checkIfExists(i)){
            i++;
            System.out.println(i);
            System.out.println(ownerService.checkIfExists(i));
        }
        owner.setOwner_id(i);
        ownerService.addOwner(owner);
    }

    // Request: Read all
    @RequestMapping(method = RequestMethod.GET, value = "/owners_filtered/{active_flag}")
    public List<Owner> getAllActiveOwners(@PathVariable String active_flag) {
        return ownerService.getAllOwners(active_flag);
    }

    //Request: Read all
    @RequestMapping("/owners")
    public List<Owner> getAllOwners(){
        return ownerService.getAllOwners("all");
    }

    //Request: Read one by id
    @RequestMapping("/owners/{id}")
    public ArrayList<Owner> readOwner(@PathVariable Integer id){
        return ownerService.getOwner(id);
    }

    //Request: Update
    @RequestMapping(method = RequestMethod.PUT, value = "/owners/{id}")
    public void updateOwner(@RequestBody Owner owner, @PathVariable Integer id){
        ownerService.updateOwner(owner,id);
    }

    //Request: Delete by id
    @RequestMapping(method = RequestMethod.DELETE, value = "/owners/{id}")
    public void deleteOwner(@PathVariable Integer id){
        ownerService.deleteOwner(id);
    }
}
