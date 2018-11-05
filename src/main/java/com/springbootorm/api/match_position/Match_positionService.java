package com.springbootorm.api.match_position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Match_positionService {

    @Autowired
    private Match_positionRepository match_positionRepository;

    //Create
    public void addMatch_position(Match_position match_position){
        match_positionRepository.save(match_position);
    }

    //Read all
    public List<Match_position> getAllMatch_positions(String active_flag){
        List<Match_position> allMatch_positions = new ArrayList<>();
        List<Match_position> activeMatch_positions = new ArrayList<>();
        List<Match_position> inActiveMatch_positions = new ArrayList<>();

        match_positionRepository.findAll().forEach(allMatch_positions::add);
        for (Match_position match_position:allMatch_positions) {
            if(match_position.getActivity()){
                activeMatch_positions.add(match_position);
            }else{
                inActiveMatch_positions.add(match_position);
            }
        }
        if(active_flag.equals("active")){
            return activeMatch_positions;
        }else if(active_flag.equals("inactive")){
            return inActiveMatch_positions;
        }else {
            return allMatch_positions;
        }
    }

    //Read one
    public ArrayList<Match_position> getMatch_position(Integer id){
        ArrayList<Match_position> match_positionList = new ArrayList<>();
        match_positionList.add(match_positionRepository.findById(id).get());
        return match_positionList;
    }

    //Update
    public void updateMatch_position(Match_position match_position, Integer id) {
        Match_position dbMatch_position = match_positionRepository.findById(id).get();
        if(match_position.getPosition() != null && !match_position.getPosition().equals("")){
            dbMatch_position.setPosition(match_position.getPosition());
        }
        match_positionRepository.save(dbMatch_position);
        }

    //Delete
    public void deleteMatch_position(Integer id) {
        Match_position match_positionToDelete = match_positionRepository.findById(id).get();
        match_positionToDelete.setActivity(false);
        updateMatch_position(match_positionToDelete,id);
    }

    //Check if exists
    public boolean checkIfExists(Integer id){
        return match_positionRepository.existsById(id);
    }
    
}
