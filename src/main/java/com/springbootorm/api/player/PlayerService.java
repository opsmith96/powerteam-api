package com.springbootorm.api.player;

import com.springbootorm.api.person.PersonRepository;
import com.springbootorm.api.team.TeamRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TeamRepository teamRepository;


    //Create
    public void addPlayer(Player player){
        playerRepository.save(player);
    }

    //Read all
    public List<Player> getAllPlayers(String active_flag){
        List<Player> allPlayers = new ArrayList<>();
        List<Player> activePlayers = new ArrayList<>();
        List<Player> inActivePlayers = new ArrayList<>();

        playerRepository.findAll().forEach(allPlayers::add);
        for (Player player:allPlayers) {
            if(player.getActivity()){
                activePlayers.add(player);
            }else{
                inActivePlayers.add(player);
            }
        }
        if(active_flag.equals("active")){
            return activePlayers;
        }else if(active_flag.equals("inactive")){
            return inActivePlayers;
        }else {
            return allPlayers;
        }
    }

    //Read one
    public ArrayList<Player> getPlayer(Integer id){
        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(playerRepository.findById(id).get());
        return playerList;
    }

    //Update
    public void updatePlayer(Player player, Integer id) {
        player.setPlayer_id(id);
        Player dbPlayer = playerRepository.findById(id).get();
        if(player.getPerson_id() != null && !player.getPerson_id().equals("")){
            dbPlayer.setPerson_id(player.getPerson_id());
        }
        if(player.getTeam_id() != null && !player.getTeam_id().equals("")){
            dbPlayer.setTeam_id(player.getTeam_id());
        }
        if(player.getNumber() != null && !player.getNumber().equals("")){
            dbPlayer.setNumber(player.getNumber());
        }
        if(player.getNormal_position() != null && !player.getNormal_position().equals("")){
            dbPlayer.setNormal_position(player.getNormal_position());
        }

        playerRepository.save(dbPlayer);
    }    

    //Delete
    public void deletePlayer(Integer id) {
        Player playerToDelete = playerRepository.findById(id).get();
        playerToDelete.setActivity(false);
        updatePlayer(playerToDelete,id);
    }

    //Check if exists
    public boolean checkIfExists(Integer id){
        return playerRepository.existsById(id);
    }


    public JSONArray getAllPlayerForUserPage() {
        JSONArray jsonArray = new JSONArray();

        List<Player> players = new ArrayList<>();

        playerRepository.findAll().forEach(players::add);
        for (Player player : players) {
            if (player.getActivity()) {
                JSONObject jsonObject = new JSONObject();

                int person_id = player.getPerson_id();
                jsonObject.put("name", personRepository.findById(person_id).get().getFirst_name() + " " + personRepository.findById(person_id).get().getLast_name());
                jsonObject.put("birthDate",personRepository.findById(person_id).get().getDate_of_birth().toString());

                int team_id = player.getTeam_id();
                jsonObject.put("team", teamRepository.findById(team_id).get().getName());

                jsonObject.put("position",player.getNormal_position());
                jsonObject.put("number",player.getNumber());

                jsonArray.add(jsonObject);

            }
        }
        return jsonArray;
    }
}
