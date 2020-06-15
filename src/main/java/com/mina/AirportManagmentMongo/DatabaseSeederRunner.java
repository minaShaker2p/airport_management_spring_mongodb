package com.mina.AirportManagmentMongo;

import com.mina.AirportManagmentMongo.domain.Aircraft;
import com.mina.AirportManagmentMongo.domain.FlightInformation;
import com.mina.AirportManagmentMongo.domain.FlightType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/*
This component should populate the database if empty.
 */

@Component
@Order(1)
public class DatabaseSeederRunner implements CommandLineRunner {
    private MongoTemplate mongoTemplate;

    public DatabaseSeederRunner(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        empty();
        seed();
    }

    private void seed() {
        // Data
        FlightInformation flightOne = new FlightInformation();
        flightOne.setDelayed(false);
        flightOne.setDepartureCity("Rome");
        flightOne.setDestinationCity("Paris");
        flightOne.setDepartureDate(LocalDate.of(2019, 3, 12));
        flightOne.setFlightType(FlightType.International);
        flightOne.setDurationMin(80);
        flightOne.setAirCraft(new Aircraft("737", 180));
        flightOne.setDescription("Flight from Rome to Paris");

        FlightInformation flightTwo = new FlightInformation();
        flightTwo.setDelayed(false);
        flightTwo.setDepartureCity("New York");
        flightTwo.setDestinationCity("Copenhagen");
        flightTwo.setDepartureDate(LocalDate.of(2019, 5, 11));
        flightTwo.setFlightType(FlightType.International);
        flightTwo.setDurationMin(600);
        flightTwo.setAirCraft(new Aircraft("747", 300));
        flightTwo.setDescription("Flight from NY to Copenhagen via Rome");

        FlightInformation flightThree = new FlightInformation();
        flightThree.setDelayed(true);
        flightThree.setDepartureCity("Bruxelles");
        flightThree.setDestinationCity("Bucharest");
        flightThree.setDepartureDate(LocalDate.of(2019, 6, 12));
        flightThree.setFlightType(FlightType.International);
        flightThree.setDurationMin(150);
        flightThree.setAirCraft(new Aircraft("A320", 170));

        FlightInformation flightFour = new FlightInformation();
        flightFour.setDelayed(true);
        flightFour.setDepartureCity("Madrid");
        flightFour.setDestinationCity("Barcelona");
        flightFour.setDepartureDate(LocalDate.of(2019, 6, 12));
        flightFour.setFlightType(FlightType.Internal);
        flightFour.setDurationMin(120);
        flightFour.setAirCraft(new Aircraft("A319", 150));

        FlightInformation flightFive = new FlightInformation();
        flightFive.setDelayed(false);
        flightFive.setDepartureCity("Las Vegas");
        flightFive.setDestinationCity("Washington");
        flightFive.setDepartureDate(LocalDate.of(2019, 6, 10));
        flightFive.setFlightType(FlightType.Internal);
        flightFive.setDurationMin(400);
        flightFive.setAirCraft(new Aircraft("A319", 150));
        flightTwo.setDescription("Flight from LA to Washington via Paris");

        FlightInformation flightSix = new FlightInformation();
        flightSix.setDelayed(false);
        flightSix.setDepartureCity("Bucharest");
        flightSix.setDestinationCity("Rome");
        flightSix.setDepartureDate(LocalDate.of(2019, 6, 13));
        flightSix.setFlightType(FlightType.International);
        flightSix.setDurationMin(110);
        flightSix.setAirCraft(new Aircraft("A321 Neo", 200));

        // Seed
        List<FlightInformation> flights = Arrays.asList(
                        flightOne,
                        flightTwo,
                        flightThree,
                        flightFour,
                        flightFive,
                        flightSix
                );
        this.mongoTemplate.insertAll(flights);
    }


    private void empty() {
        this.mongoTemplate.remove(new Query(), FlightInformation.class);
    }
}
