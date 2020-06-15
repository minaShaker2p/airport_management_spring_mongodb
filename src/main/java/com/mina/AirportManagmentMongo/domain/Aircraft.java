package com.mina.AirportManagmentMongo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Aircraft {
    private String model;
    private int nbSeats;
}
