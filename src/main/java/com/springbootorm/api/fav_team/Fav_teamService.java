package com.springbootorm.api.fav_team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Fav_teamService {
    @Autowired
    private Fav_teamRepository fav_teamRepository;

    public void addFav_team(Fav_team fav_team) {
        fav_teamRepository.save(fav_team);
    }

    //Read all
    public List<Fav_team> getAllFav_teams(String active_flag){
        List<Fav_team> allFav_teams = new ArrayList<>();
        List<Fav_team> activeFav_teams = new ArrayList<>();
        List<Fav_team> inActiveFav_teams = new ArrayList<>();

        fav_teamRepository.findAll().forEach(allFav_teams::add);
        for (Fav_team fav_team:allFav_teams) {
            if(fav_team.getActivity()){
                activeFav_teams.add(fav_team);
            }else{
                inActiveFav_teams.add(fav_team);
            }
        }
        if(active_flag.equals("active")){
            return activeFav_teams;
        }else if(active_flag.equals("inactive")){
            return inActiveFav_teams;
        }else {
            return allFav_teams;
        }
    }

    //Read one
    public ArrayList<Fav_team> getFav_team(Integer id){
        ArrayList<Fav_team> fav_teamsList = new ArrayList<>();
        fav_teamsList.add(fav_teamRepository.findById(id).get());
        return fav_teamsList;
    }

    //Update
    public void updateFav_team(Fav_team fav_team, Integer id) {
        Fav_team dbFav_team = fav_teamRepository.findById(id).get();
        if(fav_team.getTeam_id() != null && !fav_team.getTeam_id().equals("")){
            dbFav_team.setTeam_id(fav_team.getTeam_id());
        }
        if(fav_team.getRelation_id() != null && !fav_team.getRelation_id().equals("")){
            dbFav_team.setRelation_id(fav_team.getRelation_id());
        }
        fav_teamRepository.save(dbFav_team);  
      }

    public void deleteFav_team(Integer id) {
        Fav_team fav_teamToDelete = fav_teamRepository.findById(id).get();
        fav_teamToDelete.setActivity(false);
        updateFav_team(fav_teamToDelete,id);
    }

    //Check if exists
    public boolean checkIfExists(Integer id){
        return fav_teamRepository.existsById(id);
    }

}
