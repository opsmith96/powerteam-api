package com.springbootorm.api.match_goal;

import org.springframework.data.repository.CrudRepository;

public interface MatchGoalRepository extends CrudRepository<MatchGoal,Integer> {
    //public List<Person> findBy(String teamid);
}
