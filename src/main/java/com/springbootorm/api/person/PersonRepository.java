package com.springbootorm.api.person;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Integer> {
    //public List<Person> findBy(String teamid);
}
