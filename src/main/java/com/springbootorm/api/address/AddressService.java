package com.springbootorm.api.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    
    @Autowired
    private AddressRepository addressRepository;

    //Create
    public void addAddress(Address address){
        addressRepository.save(address);
    }

    //Read all
    public List<Address> getAllAddresss(String active_flag){
        List<Address> allAddresss = new ArrayList<>();
        List<Address> activeAddresss = new ArrayList<>();
        List<Address> inActiveAddresss = new ArrayList<>();

        addressRepository.findAll().forEach(allAddresss::add);
        for (Address address:allAddresss) {
            if(address.getActivity()){
                activeAddresss.add(address);
            }else{
                inActiveAddresss.add(address);
            }
        }
        if(active_flag.equals("active")){
            return activeAddresss;
        }else if(active_flag.equals("inactive")){
            return inActiveAddresss;
        }else {
            return allAddresss;
        }
    }

    //Read one

    /**
     *
     * @param id
     * @return
     */
    public ArrayList<Address> getAddress(Integer id){
        ArrayList<Address> addressList = new ArrayList<>();
        addressList.add(addressRepository.findById(id).get());
        return addressList;
    }

    //Update
    public void updateAddress(Address address, Integer id) {
        address.setAddress_id(id);
        Address dbAddress = addressRepository.findById(id).get();
        if(address.getAddress_line_1() != null && !address.getAddress_line_1().equals("")){
            dbAddress.setAddress_line_1(address.getAddress_line_1());
        }
        if(address.getAddress_line_2() != null && !address.getAddress_line_2().equals("")){
            dbAddress.setAddress_line_2(address.getAddress_line_2());
        }
        if(address.getAddress_line_3() != null && !address.getAddress_line_3().equals("")){
            dbAddress.setAddress_line_3(address.getAddress_line_3());
        }
        if(address.getPostal_code() != null && !address.getPostal_code().equals("")){
            dbAddress.setPostal_code(address.getPostal_code());
        }
        if(address.getCity() != null && !address.getCity().equals("")){
            dbAddress.setCity(address.getCity());
        }
        if(address.getCountry() != null && !address.getCountry().equals("")){
            dbAddress.setCountry(address.getCountry());
        }
        addressRepository.save(dbAddress);
        }

    //Delete
    public void deleteAddress(Integer id) {
        Address addressToDelete = addressRepository.findById(id).get();
        addressToDelete.setActivity(false);
        updateAddress(addressToDelete,id);
    }

    //Check if exists
    public boolean checkIfExists(Integer id){
        return addressRepository.existsById(id);
    }
}
