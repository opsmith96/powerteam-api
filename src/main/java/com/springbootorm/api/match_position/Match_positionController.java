package com.springbootorm.api.match_position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
public class Match_positionController {

    @Autowired
    private Match_positionService match_positionService;

    //Request: Create
    @RequestMapping(method = RequestMethod.POST, value = "/match_positions")
    public void createMatch_position(@RequestBody Match_position match_position){
        match_positionService.addMatch_position(match_position);
    }

    // Request: Read all
    @RequestMapping(method = RequestMethod.GET, value = "/match_postitons_filtered/{active_flag}")
    public List<Match_position> getAllActiveMatch_positions(@PathVariable String active_flag) {
        return match_positionService.getAllMatch_positions(active_flag);
    }

    //Request: Read all
    @RequestMapping("/match_positions")
    public List<Match_position> getAllMatch_positions(){
        return match_positionService.getAllMatch_positions("all");
    }

    //Request: Read one by id
    @RequestMapping("/match_positions/{id}")
    public ArrayList<Match_position> readMatch_position(@PathVariable Integer id){
        return match_positionService.getMatch_position(id);
    }

    //Request: Update
    @RequestMapping(method = RequestMethod.PUT, value = "/match_positions/{id}")
    public void updateMatch_position(@RequestBody Match_position match_position, @PathVariable Integer id){
        match_positionService.updateMatch_position(match_position,id);
    }

    //Request: Delete by id
    @RequestMapping(method = RequestMethod.DELETE, value = "/match_positions/{id}")
    public void deleteMatch_position(@PathVariable Integer id){
        match_positionService.deleteMatch_position(id);
    }
}
