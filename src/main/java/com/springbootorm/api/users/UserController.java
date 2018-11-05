package com.springbootorm.api.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //Request: Create
    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public void createUser(@RequestBody Users users){
        int i = 0;
        System.out.println("checkIfExists: " + userService.checkIfExists(i));
        while(userService.checkIfExists(i)){
            i++;
            System.out.println(i);
            System.out.println(userService.checkIfExists(i));
        }
        users.setUser_id(i);
        userService.addUser(users);
    }

        //Request: Read all
        @RequestMapping(method = RequestMethod.GET, value = "/users_filtered/{active_flag}")
        public List<Users> getAllPersons(@PathVariable String active_flag){
            return userService.getAllUsers(active_flag);
        }
    

    //Request: Read all
    @RequestMapping("/users")
    public List<Users> getAllUsers(){
        return userService.getAllUsers("all");
    }


    //Request: Read one by id
    @RequestMapping("/users/{id}")
    public ArrayList<Users> readUser(@PathVariable Integer id){
        return userService.getUser(id);
    }

    //Request: Update
    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    public void updateUser(@RequestBody Users users, @PathVariable Integer id){
        userService.updateUser(users,id);
    }

    //Request: Delete by id
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }

    //********************************CRUD LE'Special*******************************************************

    /*
    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}/matches")
    public void getMatchesInUser(@PathVariable Integer id){
        userService.getMatchesInUser(id);
    }
    */
}
