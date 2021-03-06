package com.mina.AirportManagmentMongo.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Setter
@Getter
@ToString(exclude = {"internalId", "airCraft", "createdAt"})
@Document("flights")
@NoArgsConstructor
public class FlightInformation {
    @Id
    private String id;

    @Indexed(unique = true)
    private String internalId;

    @Field("departure")
    @DBRef
    private Airport departureCity;

    @Field("destination")
    @DBRef
    private Airport destinationCity;
    private FlightType flightType;
    private boolean isDelayed;
    @Field("duration")
    private int durationMin;
    private LocalDate departureDate;

    private Aircraft aircraft;
    @Transient
    private LocalDate createdAt;

    private String description;

    public FlightInformation(Airport departureCity,
                             Airport destinationCity,
                             FlightType flightType,
                             int durationMin,
                             LocalDate departureDate,
                             Aircraft airCraft) {
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.flightType = flightType;
        this.durationMin = durationMin;
        this.departureDate = departureDate;
        this.aircraft = airCraft;
    }
}
