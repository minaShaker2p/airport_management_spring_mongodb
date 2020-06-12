package com.mina.AirportManagmentMongo.queries;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FlightInformationQueries {

    @Autowired
    private final MongoTemplate mongoTemplate;
}
