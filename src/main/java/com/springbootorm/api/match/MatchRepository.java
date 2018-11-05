package com.springbootorm.api.match;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MatchRepository extends CrudRepository<Match,Integer> {
    //public List<Person> findBy(String teamid);
}
