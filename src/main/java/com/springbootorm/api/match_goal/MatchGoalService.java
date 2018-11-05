package com.springbootorm.api.match_goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchGoalService {

    @Autowired
    private MatchGoalRepository matchGoalsRepository;

    //Create
    public void addMatchGoal(MatchGoal matchGoals) {
        matchGoalsRepository.save(matchGoals);
    }

    //Read all
    public List<MatchGoal> getAllMatchGoals(String active_flag){
        List<MatchGoal> allMatchGoals = new ArrayList<>();
        List<MatchGoal> activeMatchGoals = new ArrayList<>();
        List<MatchGoal> inActiveMatchGoals = new ArrayList<>();

        matchGoalsRepository.findAll().forEach(allMatchGoals::add);
        for (MatchGoal matchGoal:allMatchGoals) {
            if(matchGoal.getActivity()){
                activeMatchGoals.add(matchGoal);
            }else{
                inActiveMatchGoals.add(matchGoal);
            }
        }
        if(active_flag.equals("active")){
            return activeMatchGoals;
        }else if(active_flag.equals("inactive")){
            return inActiveMatchGoals;
        }else {
            return allMatchGoals;
        }
    }

    //Read one
    public ArrayList<MatchGoal> getMatchGoal(Integer id){
        ArrayList<MatchGoal> matchGoalsList = new ArrayList<>();
        matchGoalsList.add(matchGoalsRepository.findById(id).get());
        return matchGoalsList;
    }

    //Update
    public void updateMatchGoal(MatchGoal matchGoal, Integer id) {
        matchGoal.setMatchGoal_id(id);
        MatchGoal dbmatchGoals = matchGoalsRepository.findById(id).get();
        if(matchGoal.getPlayer_id() != null && !matchGoal.getPlayer_id().equals("")){
            dbmatchGoals.setPlayer_id(matchGoal.getPlayer_id());
        }
        if(matchGoal.getGoal_type_id() != null && !matchGoal.getGoal_type_id().equals("")){
            dbmatchGoals.setGoal_type_id(matchGoal.getGoal_type_id());
        }
        if(matchGoal.getMatch_Id() != null && !matchGoal.getMatch_Id().equals("")){
            dbmatchGoals.setMatch_Id(matchGoal.getMatch_Id());
        }
        if(matchGoal.getDescription() != null && !matchGoal.getDescription().equals("")){
            dbmatchGoals.setDescription(matchGoal.getDescription());
        }
        matchGoalsRepository.save(dbmatchGoals);  
      }

    //Delete
    public void deleteMatchGoal(Integer id) {
        MatchGoal matchGoalToDelete = matchGoalsRepository.findById(id).get();
        matchGoalToDelete.setActivity(false);
        updateMatchGoal(matchGoalToDelete,id);
    }

    //Check if exists
    public boolean checkIfExists(Integer id){
        return matchGoalsRepository.existsById(id);
    }

}