package com.springbootorm.api.goal_type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
public class GoalTypeController {

    @Autowired
    private GoalTypeService goalTypeService;

    // Request: Create
    @RequestMapping(method = RequestMethod.POST, value = "/goal_types")
    public void createGoalType(@RequestBody GoalType goalType) {
        int i = 0;
        System.out.println("checkIfExists: " + goalTypeService.checkIfExists(i));
        while (goalTypeService.checkIfExists(i)) {
            i++;
            System.out.println(i);
            System.out.println(goalTypeService.checkIfExists(i));
        }
        goalType.setGoalType_id(i);
        goalTypeService.addGoalType(goalType);
    }

    // Request: Read all
    @RequestMapping("/goal_types")
    public List<GoalType> getAllGoalTypes() {
        return goalTypeService.getAllGoalTypes("all");
    }

    // Request: Read all
    @RequestMapping(method = RequestMethod.GET, value = "/goalTypees_filtered/{active_flag}")
    public List<GoalType> getAllGoalTypes(@PathVariable String active_flag) {
        return goalTypeService.getAllGoalTypes(active_flag);
    }

    // Request: Read one by id
    @RequestMapping("/goal_types/{id}")
    public ArrayList<GoalType> readGoalType(@PathVariable Integer id) {
        return goalTypeService.getGoalType(id);
    }

    // Request: Update
    @RequestMapping(method = RequestMethod.PUT, value = "/goal_types/{id}")
    public void updateGoalType(@RequestBody GoalType goalType, @PathVariable Integer id) {
        goalTypeService.updateGoalType(goalType, id);
    }

    // Request: Delete by id
    @RequestMapping(method = RequestMethod.DELETE, value = "/goal_types/{id}")
    public void deleteGoalType(@PathVariable Integer id) {
        goalTypeService.deleteGoalType(id);
    }
}
