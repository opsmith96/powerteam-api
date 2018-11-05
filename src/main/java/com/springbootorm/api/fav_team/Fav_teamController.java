package com.springbootorm.api.fav_team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
public class Fav_teamController{

        @Autowired
        private Fav_teamService fav_teamService;

        //Request: Create
        @RequestMapping(method = RequestMethod.POST, value = "/fav_teams")
        public void createFav_team(@RequestBody Fav_team fav_team){
            int i = 0;
            System.out.println("checkIfExists: " + fav_teamService.checkIfExists(i));
            while(fav_teamService.checkIfExists(i)){
                    i++;
                    System.out.println(i);
                    System.out.println(fav_teamService.checkIfExists(i));
            }
            fav_team.setRelation_id(i);
            fav_teamService.addFav_team(fav_team);
        }

        //Request: Read all
        @RequestMapping("/fav_teams")
        public List<Fav_team> getAllFav_teams(){
            return fav_teamService.getAllFav_teams("all");
        }

    //Request: Read all
    @RequestMapping(method = RequestMethod.GET, value = "/fav_teames_filtered/{active_flag}")
    public List<Fav_team> getAllFav_teams(@PathVariable String active_flag){
        return fav_teamService.getAllFav_teams(active_flag);
    }


        //Request: Read one by id
        @RequestMapping("/fav_teams/{id}")
        public ArrayList<Fav_team> readFav_teams(@PathVariable Integer id){
            return fav_teamService.getFav_team(id);
        }

        //Request: Update
        @RequestMapping(method = RequestMethod.PUT, value = "/fav_teams/{id}")
        public void updateFav_team(@RequestBody Fav_team fav_team, @PathVariable Integer id){
            fav_teamService.updateFav_team(fav_team,id);
        }

        //Request: Delete by id
        @RequestMapping(method = RequestMethod.DELETE, value = "/fav_teams/{id}")
        public void deleteFav_team(@PathVariable Integer id){
            fav_teamService.deleteFav_team(id);
        }

        //********************************CRUD LE'Special*******************************************************

    /*
    @RequestMapping(method = RequestMethod.GET, value = "/fav_team/{id}/matches")
    public void getMatchesInFav_teams(@PathVariable Integer id){
        fav_teamsService.getMatchesInFav_teams(id);
    }
    */
}
