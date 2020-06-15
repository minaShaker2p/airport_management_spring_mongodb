package com.mina.AirportManagmentMongo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("airports")
@Setter
@Getter
@AllArgsConstructor
public class Airport {

    @Id
    private String id;
    private String name;
    private String city;
    private int passengersServed;
}
