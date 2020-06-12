package com.mina.AirportManagmentMongo.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Setter
@Getter
@ToString(exclude = {"internalId","airCraft","createdAt"})
@Document("flights")
@NoArgsConstructor
public class FlightInformation {
    @Id
    private String id;

    @Indexed(unique = true)
    private String internalId;

    @Field("departure")
    private String departureCity;

    @Field("destination")
    private String destinationCity;
    private FlightType flightType;
    private boolean isDelayed;
    private int durationMin;
    private LocalDate departureDate;
    private AirCraft airCraft;
    @Transient
    private LocalDate createdAt;

    public FlightInformation(String departureCity,
                             String destinationCity,
                             FlightType flightType,
                             int durationMin,
                             LocalDate departureDate,
                             AirCraft airCraft) {
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.flightType = flightType;
        this.durationMin = durationMin;
        this.departureDate = departureDate;
        this.airCraft = airCraft;
    }
}
