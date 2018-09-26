/*
 * Copyright (c) 2018. Walter Chen
 */

package com.cicidi.springwebflux.service;

import com.cicidi.springwebflux.domain.People;
import com.cicidi.springwebflux.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author cicidi on 9/12/18
 */

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    PeopleRepository peopleRepository;

    @PostMapping("/upsert")
    @Override
    public Mono<ServiceResponse> createOrUpdate(Object p) {
        return null;
    }

    @GetMapping("/find")
    @Override
    public Mono<People> findByFirstNameAndLastName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return peopleRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("/findAll")
    @Override
    public Flux<People> findAll() {
        return peopleRepository.findAll();
    }

    /*
    * Actually, you know, you can merge all these 3 get Api into ,by send one ServiceRequest object as a parameter.
    * Here I want to demo 3 different search implementation, so I am not using ServiceRequest.
    * */
    @GetMapping("/findNearBy")
    @Override
    public Flux<ServiceRequest> findNearBy(double[] targe, int rangeInKilometer) {
        return null;
    }
}
