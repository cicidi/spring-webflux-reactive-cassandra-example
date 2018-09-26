/*
 * Copyright (c) 2018. Walter Chen
 */

package com.cicidi.springwebflux.service;

import com.cicidi.springwebflux.domain.People;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author cicidi on 9/12/18
 */
public interface PeopleService<People> {

    Mono<ServiceResponse<People>> createOrUpdate(People p);

    Mono<ServiceResponse<People>> findByFirstNameAndLastName(String firstName, String lastName);

    Flux<ServiceRequest<People>> findAll();

    Flux<ServiceRequest<People>> findNearBy(double[] targe, int rangeInKilometer);

}
