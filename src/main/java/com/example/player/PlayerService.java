package com.example.player;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    //Create
    public void addPlayer(Player player){
        playerRepository.save(player);
    }

    //Read all
    public List<Player> getAllPlayers(){
        List<Player> players = new ArrayList<>();
        playerRepository.findAll().forEach(players::add);
        return players;
    }

    //Read one
    public Player getPlayer(int id){
        return playerRepository.findById(id).get();
    }

    //Update
    public void updatePlayer(Player player, int id) {
        playerRepository.save(player);
    }

    //Delete
    public void deletePlayer(int id) {
        playerRepository.deleteById(id);
    }


}
