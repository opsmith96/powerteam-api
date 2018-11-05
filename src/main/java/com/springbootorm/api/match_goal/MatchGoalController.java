package com.springbootorm.api.match_goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
public class MatchGoalController {

    @Autowired
    private MatchGoalService matchGoalService;

    //Request: Create
    @RequestMapping(method = RequestMethod.POST, value = "/match_goals")
    public void createMatchGoal(@RequestBody MatchGoal matchGoal){
        int i = 0;
        System.out.println("checkIfExists: " + matchGoalService.checkIfExists(i));
        while(matchGoalService.checkIfExists(i)){
            i++;
            System.out.println(i);
            System.out.println(matchGoalService.checkIfExists(i));
        }
        matchGoal.setMatchGoal_id(i);
        matchGoalService.addMatchGoal(matchGoal);
    }

    //Request: Read all
    @RequestMapping("/match_goals")
    public List<MatchGoal> getAllMatchGoals(){
        return matchGoalService.getAllMatchGoals("all");
    }

    //Request: Read all
    @RequestMapping(method = RequestMethod.GET, value = "/matchGoales_filtered/{active_flag}")
    public List<MatchGoal> getAllMatchGoals(@PathVariable String active_flag){
        return matchGoalService.getAllMatchGoals(active_flag);
    }

    //Request: Read one by id
    @RequestMapping("/match_goals/{id}")
    public ArrayList<MatchGoal> readMatchGoal(@PathVariable Integer id){
        return matchGoalService.getMatchGoal(id);
    }

    //Request: Update
    @RequestMapping(method = RequestMethod.PUT, value = "/match_goals/{id}")
    public void updateMatchGoal(@RequestBody MatchGoal matchGoal, @PathVariable Integer id){
        matchGoalService.updateMatchGoal(matchGoal,id);
    }

    //Request: Delete by id
    @RequestMapping(method = RequestMethod.DELETE, value = "/match_goals/{id}")
    public void deleteMatchGoal(@PathVariable Integer id){
        matchGoalService.deleteMatchGoal(id);
    }

}
