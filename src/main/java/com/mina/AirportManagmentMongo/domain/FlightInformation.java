package com.mina.AirportManagmentMongo.domain;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class FlightInformation {
    private String id;
    private String departureCity;
    private String destinationCity;
    private FlightType flightType;
    private boolean isDelayed;
    private int durationMin;
    private LocalDate departureDate;
    private AirCraft airCraft;
    private LocalDate createdAt;

}
