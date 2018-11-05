package com.springbootorm.api.goal_type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoalTypeService {

    @Autowired
    private GoalTypeRepository goalTypeRepository;

    //Create
    public void addGoalType(GoalType goalType){
        goalTypeRepository.save(goalType);
    }

    //Read all
    public List<GoalType> getAllGoalTypes(String active_flag){
        List<GoalType> allGoalTypes = new ArrayList<>();
        List<GoalType> activeGoalTypes = new ArrayList<>();
        List<GoalType> inActiveGoalTypes = new ArrayList<>();

        goalTypeRepository.findAll().forEach(allGoalTypes::add);
        for (GoalType goalType:allGoalTypes) {
            if(goalType.getActivity()){
                activeGoalTypes.add(goalType);
            }else{
                inActiveGoalTypes.add(goalType);
            }
        }
        if(active_flag.equals("active")){
            return activeGoalTypes;
        }else if(active_flag.equals("inactive")){
            return inActiveGoalTypes;
        }else {
            return allGoalTypes;
        }
    }

    //Read one
    public ArrayList<GoalType> getGoalType(Integer id){
        ArrayList<GoalType> goalTypeList = new ArrayList<>();
        goalTypeList.add(goalTypeRepository.findById(id).get());
        return goalTypeList;
    }

    //Update
    public void updateGoalType(GoalType goalType, Integer id) {
        goalType.setGoalType_id(id);
        GoalType dbGoalType = goalTypeRepository.findById(id).get();
        if(goalType.getType() != null && !goalType.getType().equals("")){
            dbGoalType.setType(goalType.getType());
        }

        goalTypeRepository.save(dbGoalType);   
     }

    //Delete
    public void deleteGoalType(Integer id) {
        GoalType goalTypeToDelete = goalTypeRepository.findById(id).get();
        goalTypeToDelete.setActivity(false);
        updateGoalType(goalTypeToDelete,id);
    }

    //Check if exists
    public boolean checkIfExists(Integer id){
        return goalTypeRepository.existsById(id);
    }
    
}
