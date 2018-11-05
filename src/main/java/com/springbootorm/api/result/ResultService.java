package com.springbootorm.api.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    //Create
    public void addResult(Result result){
        resultRepository.save(result);
    }

    //Read all
    public List<Result> getAllResults(String active_flag){
        List<Result> allResults = new ArrayList<>();
        List<Result> activeResults = new ArrayList<>();
        List<Result> inActiveResults = new ArrayList<>();

        resultRepository.findAll().forEach(allResults::add);
        for (Result result:allResults) {
            if(result.getActivity()){
                activeResults.add(result);
            }else{
                inActiveResults.add(result);
            }
        }
        if(active_flag.equals("active")){
            return activeResults;
        }else if(active_flag.equals("inactive")){
            return inActiveResults;
        }else {
            return allResults;
        }
    }

    //Read one
    public ArrayList<Result> getResult(Integer id){
        ArrayList<Result> resultList = new ArrayList<>();
        resultList.add(resultRepository.findById(id).get());
        return resultList;
    }

    //Update
    public void updateResult(Result result, Integer id) {
        Result dbResult = resultRepository.findById(id).get();
        if(result.getMatch_id() != null && !result.getMatch_id().equals("")){
            dbResult.setMatch_id(result.getMatch_id());
        }
        if(result.getTeam_id() != null && !result.getTeam_id().equals("")){
            dbResult.setTeam_id(result.getTeam_id());
        }
        if(result.getScore() != null && !result.getScore().equals("")){
            dbResult.setScore(result.getScore());
        }
        if(result.getResult() != null && !result.getResult().equals("")){
            dbResult.setResult(result.getResult());
        }

        resultRepository.save(dbResult);
    }    

    //Delete
    public void deleteResult(Integer id) {
        Result resultToDelete = resultRepository.findById(id).get();
        resultToDelete.setActivity(false);
        updateResult(resultToDelete,id);    }

    //Check if exists
    public boolean checkIfExists(Integer id){
        return resultRepository.existsById(id);
    }
    
}
