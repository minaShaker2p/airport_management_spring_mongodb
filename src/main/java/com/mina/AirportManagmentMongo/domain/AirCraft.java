package com.mina.AirportManagmentMongo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
public class AirCraft {
    private String model;
    private int nbSeats;
}
