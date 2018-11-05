package com.springbootorm.api.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    //Request: Create
    @RequestMapping(method = RequestMethod.POST, value = "/matches")
    public void createMatch(@RequestBody Match match){
        int i = 0;
        System.out.println("checkIfExists: " + matchService.checkIfExists(i));
        while(matchService.checkIfExists(i)){
            i++;
            System.out.println(i);
            System.out.println(matchService.checkIfExists(i));
        }
        match.setMatch_id(i);
        matchService.addMatch(match);
    }

    //Request: Read all
    @RequestMapping("/matches")
    public List<Match> getAllMatchs(){
        return matchService.getAllMatchs("all");
    }

    //Request: Read all
    @RequestMapping(method = RequestMethod.GET, value = "/matches_filtered/{active_flag}")
    public List<Match> getAllMatchs(@PathVariable String active_flag){
        return matchService.getAllMatchs(active_flag);
    }

    //Request: Read one by id
    @RequestMapping("/matches/{id}")
    public ArrayList<Match> readMatch(@PathVariable Integer id){
        return matchService.getMatch(id);
    }

    //Request: Update
    @RequestMapping(method = RequestMethod.PUT, value = "/matches/{id}")
    public void updateMatch(@RequestBody Match match, @PathVariable Integer id){
        matchService.updateMatch(match,id);
    }

    //Request: Delete by id
    @RequestMapping(method = RequestMethod.DELETE, value = "/matches/{id}")
    public void deleteMatch(@PathVariable Integer id){
        matchService.deleteMatch(id);
    }

}
