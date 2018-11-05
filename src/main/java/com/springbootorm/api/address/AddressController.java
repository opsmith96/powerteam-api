package com.springbootorm.api.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    //Request: Create
    @RequestMapping(method = RequestMethod.POST, value = "/addresses")
    public void createAddress(@RequestBody Address address){
        int i = 0;
        System.out.println("checkIfExists: " + addressService.checkIfExists(i));
        while(addressService.checkIfExists(i)){
            i++;
            System.out.println(i);
            System.out.println(addressService.checkIfExists(i));
        }
        address.setAddress_id(i);
        addressService.addAddress(address);
    }

    //Request: Read all
    @RequestMapping("/addresses")
    public List<Address> getAllAddresss(){
        return addressService.getAllAddresss("all");
    }

    //Request: Read all
    @RequestMapping(method = RequestMethod.GET, value = "/addresses_filtered/{active_flag}")
    public List<Address> getAllAddresss(@PathVariable String active_flag){
        return addressService.getAllAddresss(active_flag);
    }

    //Request: Read one by id
    @RequestMapping("/addresses/{id}")
    public ArrayList<Address> readAddress(@PathVariable Integer id){
        return addressService.getAddress(id);
    }

    //Request: Update
    @RequestMapping(method = RequestMethod.PUT, value = "/addresses/{id}")
    public void updateAddress(@RequestBody Address address, @PathVariable Integer id){
        addressService.updateAddress(address,id);
    }

    //Request: Delete by id
    @RequestMapping(method = RequestMethod.DELETE, value = "/addresses/{id}")
    public void deleteAddress(@PathVariable Integer id){
        addressService.deleteAddress(id);
    }
    
}
