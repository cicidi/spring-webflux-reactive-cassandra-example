/*
 * Copyright (c) 2018. Walter Chen
 */

package com.cicidi.springwebflux;

import com.cicidi.springwebflux.domain.Address;
import com.cicidi.springwebflux.domain.Geolocationinfo;
import com.cicidi.springwebflux.domain.People;
import com.cicidi.springwebflux.domain.PeoplePrimaryKey;
import com.cicidi.springwebflux.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author cicidi on 9/11/18
 */

@SpringBootApplication
@EnableReactiveCassandraRepositories(basePackages = "com.cicidi.springwebflux")
public class SpringWebfluxApplication implements CommandLineRunner {

    @Autowired
    private PeopleRepository peopleRepository;

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringWebfluxApplication.class).run(args);
    }

    @Override
    public void run(String[] args) {
        createData(peopleRepository);
    }

    private static void createData(PeopleRepository peopleRepository) {
        List<People> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(createAPeople(i));
        }

        list.forEach(
                people -> {
                    Mono<People> p = peopleRepository.findByFirstNameAndLastName(people.getPeoplePrimaryKey().getFirstName(), people.getPeoplePrimaryKey().getLastName());
                    p.switchIfEmpty(peopleRepository.insert(people))
                            .subscribe(
                                    exist -> System.out.println("People : " + exist.getPeoplePrimaryKey().getFirstName() + " saved ")
                            );
                });
    }


    public static People createAPeople(int i) {
        return People.builder()
                .peoplePrimaryKey(new PeoplePrimaryKey
                        ("FirstName_" + i, "LastName_" + i,
                                UUID.randomUUID()))
                .address(
                        Address.builder()
                                .country("Country_" + i)
                                .number("number_" + i)
                                .state("state_" + i)
                                .zip(i)
                                .street("street_" + i)
                                .geolocationinfo(Geolocationinfo.builder().longtitude(getRandomDoubleBetweenRange(121.4552, 122.4552))
                                        .latitude(getRandomDoubleBetweenRange(36.8541, 37.8541)).build())
                                .build()
                ).age(i).occupation("Occupation_" + i).build();

    }


    public static double getRandomDoubleBetweenRange(double min, double max) {
        double x = (Math.random() * ((max - min) + 1)) + min;
        return x;
    }
}
