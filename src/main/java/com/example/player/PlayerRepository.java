package com.example.player;

import org.springframework.data.repository.CrudRepository;

import java.awt.*;

public interface PlayerRepository extends CrudRepository<Player,Integer> {
    //public List<Player> findByTeamId(String teamid);
}
