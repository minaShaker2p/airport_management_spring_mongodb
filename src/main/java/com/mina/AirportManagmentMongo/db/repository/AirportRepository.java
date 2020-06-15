package com.mina.AirportManagmentMongo.db.repository;

import com.mina.AirportManagmentMongo.domain.Airport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AirportRepository extends MongoRepository<Airport,String> {
}
