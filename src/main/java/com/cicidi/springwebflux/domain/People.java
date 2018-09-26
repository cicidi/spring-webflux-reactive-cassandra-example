/*
 * Copyright (c) 2018. Walter Chen
 */

package com.cicidi.springwebflux.domain;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * @author cicidi on 9/11/18
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Table(value = People.tablename)
public class People {

    public static final String tablename = "people";

    @PrimaryKey
    private PeoplePrimaryKey peoplePrimaryKey;

    private Address address;

    private int age;

    private String occupation;

}
