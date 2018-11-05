package com.springbootorm.api.location;

import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location,Integer> {
    //public List<Location> findByTeamId(String teamid);
}
