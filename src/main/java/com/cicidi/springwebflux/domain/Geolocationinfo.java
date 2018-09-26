/*
 * Copyright (c) 2018. Walter Chen
 */

package com.cicidi.springwebflux.domain;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

/**
 * @author cicidi on 9/25/18
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@UserDefinedType

public class Geolocationinfo {
    double latitude;
    double longtitude;

}
