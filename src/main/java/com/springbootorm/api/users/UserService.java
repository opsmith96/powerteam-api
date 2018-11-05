package com.springbootorm.api.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(Users users) {
        userRepository.save(users);
    }

    // Read all
    public List<Users> getAllUsers(String active_flag) {
        List<Users> allUsers = new ArrayList<>();
        List<Users> activeUsers = new ArrayList<>();
        List<Users> inActiveUsers = new ArrayList<>();

        userRepository.findAll().forEach(allUsers::add);
        for (Users user : allUsers) {
            if (user.getActivity()) {
                activeUsers.add(user);
            } else {
                inActiveUsers.add(user);
            }
        }
        if (active_flag.equals("active")) {
            return activeUsers;
        } else if (active_flag.equals("inactive")) {
            return inActiveUsers;
        } else {
            return allUsers;
        }
    }

    // Read one
    public ArrayList<Users> getUser(Integer id) {
        ArrayList<Users> usersList = new ArrayList<>();
        usersList.add(userRepository.findById(id).get());
        return usersList;
    }

    // Update
    public void updateUser(Users users, Integer id) {
        users.setUser_id(id);;
        Users dbUser = userRepository.findById(id).get();
        if(users.getUser_name() != null && !users.getUser_name().equals("")){
            dbUser.setUser_name(users.getUser_name());
        }
        if(users.getPassword() != null && !users.getPassword().equals("")){
            dbUser.setPassword(users.getPassword());
        }

        userRepository.save(dbUser);
    }

    public void deleteUser(Integer id) {
        Users userToDelete = userRepository.findById(id).get();
        userToDelete.setActivity(false);
        updateUser(userToDelete, id);
    }

    // Check if exists
    public boolean checkIfExists(Integer id) {
        return userRepository.existsById(id);
    }

    /*
     * public List<Match> getMatchesInUser(Integer userId){ return
     * userRepository.findByUser_id(userId); }
     */
}
