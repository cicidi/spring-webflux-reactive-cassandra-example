/*
 * Copyright (c) 2018. Walter Chen
 */

package com.cicidi.springwebflux.domain;


import lombok.*;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.util.Objects;
import java.util.UUID;

/**
 * @author cicidi on 9/11/18
 */

@PrimaryKeyClass
public class PeoplePrimaryKey {

    /*
    * Here , in order to demonstrate findByFirstName and lastName user customized query,
    * and also demo how cassandra split data in each data node.
    * I am assuming that all people firstName + lastName are unique.
    * I am using uuid as cluster key;
    * */

    @PrimaryKeyColumn(name = "firstname", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String firstName;

    @PrimaryKeyColumn(name = "lastname", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String lastName;

    @PrimaryKeyColumn(name = "uuid", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    private UUID uuid;

    public PeoplePrimaryKey() {
    }

    public PeoplePrimaryKey(String firstName, String lastName, UUID uuid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.uuid = uuid;
    }

    /*
    * lombok does not work for Cassandra PrimaryKeyClass
    * */

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeoplePrimaryKey that = (PeoplePrimaryKey) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, uuid);
    }
}
