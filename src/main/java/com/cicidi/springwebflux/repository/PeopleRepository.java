/*
 * Copyright (c) 2018. Walter Chen
 */

package com.cicidi.springwebflux.repository;

import com.cicidi.springwebflux.domain.People;
import com.cicidi.springwebflux.domain.PeoplePrimaryKey;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * @author cicidi on 9/12/18
 */

@Repository
public interface PeopleRepository extends ReactiveCassandraRepository<People, PeoplePrimaryKey> {

    @Query("select * from people where firstName=?0 and lastName=?1 ")
    Mono<People> findByFirstNameAndLastName(String firstName, String lastName);
}
