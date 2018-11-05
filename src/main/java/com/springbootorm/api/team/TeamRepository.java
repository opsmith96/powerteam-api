package com.springbootorm.api.team;


import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team,Integer> {
    //public List<Team> findByTeamId(String teamid);
}
