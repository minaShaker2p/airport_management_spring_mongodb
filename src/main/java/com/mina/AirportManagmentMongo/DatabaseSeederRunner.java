package com.mina.AirportManagmentMongo;

import com.mina.AirportManagmentMongo.db.repository.AirportRepository;
import com.mina.AirportManagmentMongo.db.repository.FlightInformationRepository;
import com.mina.AirportManagmentMongo.domain.Aircraft;
import com.mina.AirportManagmentMongo.domain.Airport;
import com.mina.AirportManagmentMongo.domain.FlightInformation;
import com.mina.AirportManagmentMongo.utils.FlightPrinter;
import com.mina.AirportManagmentMongo.domain.FlightType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/*
This component should populate the database if empty.
 */

@Component
@Order(1)
@RequiredArgsConstructor
public class DatabaseSeederRunner implements CommandLineRunner {


    @Autowired
    private final FlightInformationRepository flightInformationRepository;
    @Autowired
    private final MongoTemplate mongoTemplate;
    @Autowired
    private final AirportRepository airportRepository;


    @Override
    public void run(String... args) throws Exception {
        empty();
        seed();
    }

    private void seed() {
        // Airports
        Airport rome = new Airport(
                "1d1aab22-670b-48cb-a027-721e2055731f",
                "Leonardo da Vinci",
                "Rome",
                42995119);
        Airport paris = new Airport(
                "d04a8c26-7527-407c-81ef-680e5de34cea",
                "Charles de Gaulle",
                "Paris",
                72229723);
        Airport copenhagen = new Airport(
                "7ed990d2-6471-485d-931e-c77729dc0f25",
                "Copenhagen Airport",
                "Copenhagen",
                30298531);

        // Flight data
        FlightInformation flightOne = new FlightInformation();
        flightOne.setId("b8b50563-ca90-4afc-9d43-b674892a525c");
        flightOne.setDelayed(false);
        flightOne.setDepartureCity(rome);
        flightOne.setDestinationCity(paris);
        flightOne.setDepartureDate(LocalDate.of(2019, 3, 12));
        flightOne.setFlightType(FlightType.International);
        flightOne.setDurationMin(80);
        flightOne.setAircraft(new Aircraft("737", 180));

        FlightInformation flightTwo = new FlightInformation();
        flightTwo.setId("c0725fbb-eebb-4aae-8b41-3887793d0c50");
        flightTwo.setDelayed(false);
        flightTwo.setDepartureCity(paris);
        flightTwo.setDestinationCity(copenhagen);
        flightTwo.setDepartureDate(LocalDate.of(2019, 5, 11));
        flightTwo.setFlightType(FlightType.International);
        flightTwo.setDurationMin(600);
        flightTwo.setAircraft(new Aircraft("747", 300));

        // Seed
        List<Airport> airports = Arrays.asList(rome, paris, copenhagen);
        airportRepository.insert(airports);

        List<FlightInformation> flights = Arrays.asList(flightOne, flightTwo);
        flightInformationRepository.insert(flights);

        FlightPrinter.print(flights);

        //Count

        long count = flightInformationRepository.count();
        System.out.println("Total flights in database: " + count);

        // Print

        List<FlightInformation> flightsInDb = flightInformationRepository.findAll(Sort.by("departure").ascending());
        FlightPrinter.print(flightsInDb);
        System.out.println("\n----Seeder Finished----\n");
    }


    private void empty() {
        mongoTemplate.dropCollection(Airport.class);
        mongoTemplate.dropCollection(FlightInformation.class);
    }
}
