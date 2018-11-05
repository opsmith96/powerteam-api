package com.springbootorm.api.coach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
public class CoachController {
    @Autowired
    private CoachService coachService;

    //Request: Create
    @RequestMapping(method = RequestMethod.POST, value = "/coaches")
    public void createCoach(@RequestBody Coach coach){
        int i = 0;
        System.out.println("checkIfExists: " + coachService.checkIfExists(i));
        while(coachService.checkIfExists(i)){
            i++;
            System.out.println(i);
            System.out.println(coachService.checkIfExists(i));
        }
        coach.setCoach_id(i);
        coachService.addCoach(coach);
    }

    //Request: Read all
    @RequestMapping("/coaches")
    public List<Coach> getAllCoachs(){
        return coachService.getAllCoachs("all");
    }

    //Request: Read all
    @RequestMapping(method = RequestMethod.GET, value = "/coaches_filtered/{active_flag}")
    public List<Coach> getAllCoachs(@PathVariable String active_flag){
        return coachService.getAllCoachs(active_flag);
    }

    //Request: Read one by id
    @RequestMapping("/coaches/{id}")
    public ArrayList<Coach> readCoach(@PathVariable Integer id){
        return coachService.getCoach(id);
    }

    //Request: Update
    @RequestMapping(method = RequestMethod.PUT, value = "/coaches/{id}")
    public void updateCoach(@RequestBody Coach coach, @PathVariable Integer id){
        coachService.updateCoach(coach,id);
    }

    //Request: Delete by id
    @RequestMapping(method = RequestMethod.DELETE, value = "/coaches/{id}")
    public void deleteCoach(@PathVariable Integer id){
        coachService.deleteCoach(id);
    }
}
