package com.springbootorm.api.player;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player,Integer> {
    //public List<Player> findByTeamId(String teamid);
}
