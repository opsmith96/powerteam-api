package com.springbootorm.api.season;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
public class SeasonController {
    @Autowired
    private SeasonService seasonService;

    //Request: Create
    @RequestMapping(method = RequestMethod.POST, value = "/seasons")
    public void createSeason(@RequestBody Season season){
        int i = 0;
        System.out.println("checkIfExists: " + seasonService.checkIfExists(i));
        while(seasonService.checkIfExists(i)){
            i++;
            System.out.println(i);
            System.out.println(seasonService.checkIfExists(i));
        }
        season.setSeason_id(i);
        seasonService.addSeason(season);
    }

        //Request: Read all
        @RequestMapping(method = RequestMethod.GET, value = "/seasons_filtered/{active_flag}")
        public List<Season> getallSeasons(@PathVariable String active_flag){
            return seasonService.getAllSeasons(active_flag);
        }

    //Request: Read all
    @RequestMapping("/seasons")
    public List<Season> getAllSeasons(){
        return seasonService.getAllSeasons("all");
    }
    

    //Request: Read one by id
    @RequestMapping("/seasons/{id}")
    public ArrayList<Season> readSeason(@PathVariable Integer id){
        return seasonService.getSeason(id);
    }

    //Request: Update
    @RequestMapping(method = RequestMethod.PUT, value = "/seasons/{id}")
    public void updateSeason(@RequestBody Season season, @PathVariable Integer id){
        seasonService.updateSeason(season,id);
    }

    //Request: Delete by id
    @RequestMapping(method = RequestMethod.DELETE, value = "/seasons/{id}")
    public void deleteSeason(@PathVariable Integer id){
        seasonService.deleteSeason(id);
    }

    //********************************CRUD LE'Special*******************************************************

    /*
    @RequestMapping(method = RequestMethod.GET, value = "/seasons/{id}/matches")
    public void getMatchesInSeason(@PathVariable Integer id){
        seasonService.getMatchesInSeason(id);
    }
    */

}
