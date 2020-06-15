package com.mina.AirportManagmentMongo.db.repository;

import com.mina.AirportManagmentMongo.domain.FlightInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightInformationRepository extends MongoRepository<FlightInformation, String> {

    List<FlightInformation> findByDepartureCityAndDestinationCity(String departure, String destination);

    @Query("{'aircraft.nbSeats' : {$gte : ?0}}")
    List<FlightInformation> findByMinAircraftNbSeats(int minNbSeats);

    @Query("{'departure.$city' : ?0}")
    List<FlightInformation> findByDepartureCity(String departure);

}
