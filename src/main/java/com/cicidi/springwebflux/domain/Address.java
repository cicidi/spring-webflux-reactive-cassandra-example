/*
 * Copyright (c) 2018. Walter Chen
 */

package com.cicidi.springwebflux.domain;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

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

/**
 * This annotation is required here otherwise you get only primitive types and Collections or Maps of primitive types are allowed
 */
@UserDefinedType

public class Address {

    private String street;

    private String number;

    private int zip;

    private String state;

    private String country;

    private Geolocationinfo geolocationinfo;
}
