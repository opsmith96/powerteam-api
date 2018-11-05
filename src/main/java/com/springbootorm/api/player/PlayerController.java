package com.springbootorm.api.player;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    //Request: Create
    @RequestMapping(method = RequestMethod.POST, value = "/players")
    public void createPlayer(@RequestBody Player player){
        int i = 0;
        System.out.println("checkIfExists: " + playerService.checkIfExists(i));
        while(playerService.checkIfExists(i)){
            i++;
            System.out.println(i);
            System.out.println(playerService.checkIfExists(i));
        }
        player.setPlayer_id(i);
        playerService.addPlayer(player);
    }

        //Request: Read all
        @RequestMapping(method = RequestMethod.GET, value = "/players_filtered/{active_flag}")
        public List<Player> getAllPlayers(@PathVariable String active_flag){
            return playerService.getAllPlayers(active_flag);
        }

    //Request: Read all
    @RequestMapping("/players")
    public List<Player> getAllPlayers(){
        return playerService.getAllPlayers("all");
    }

    //Request: Read one by id
    @RequestMapping("/players/{id}")
    public ArrayList<Player> readPlayer(@PathVariable Integer id){
        return playerService.getPlayer(id);
    }



    // Request: Read one - filtered for user page
    @RequestMapping(method = RequestMethod.GET, value = "/playersForUser/{name}")
    public JSONObject getTeamForUserPage(@PathVariable String name) {
        JSONArray playerJsonArray = getAllPlayersForUserPage();
        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < playerJsonArray.size(); i++){
            if(playerJsonArray.get(i).toString().contains(name)){
                return (JSONObject) playerJsonArray.get(i);
            }
        }
        System.out.println(jsonObject);
        return jsonObject;
    }

    // Request: Read all - filtered for user page
    @RequestMapping(method = RequestMethod.GET, value = "/playersForUser")
    private JSONArray getAllPlayersForUserPage() {
        return playerService.getAllPlayerForUserPage();
    }


    //Request: Update
    @RequestMapping(method = RequestMethod.PUT, value = "/players/{id}")
    public void updatePlayer(@RequestBody Player player, @PathVariable Integer id){
        playerService.updatePlayer(player,id);
    }

    //Request: Delete by id
    @RequestMapping(method = RequestMethod.DELETE, value = "/players/{id}")
    public void deletePlayer(@PathVariable Integer id){
        playerService.deletePlayer(id);
    }

}
