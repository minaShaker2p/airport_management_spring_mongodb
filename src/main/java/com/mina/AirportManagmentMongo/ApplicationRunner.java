package com.mina.AirportManagmentMongo;

import com.mina.AirportManagmentMongo.domain.AirCraft;
import com.mina.AirportManagmentMongo.domain.FlightInformation;
import com.mina.AirportManagmentMongo.domain.FlightType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ApplicationRunner  implements CommandLineRunner {

    private MongoTemplate mongoTemplate;

    public ApplicationRunner(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public void run(String... args) throws Exception {
        FlightInformation flight= new FlightInformation("Germany","Egypt",
                FlightType.International,5,
                LocalDate.now(),
                new AirCraft("A329",30));
        mongoTemplate.save(flight);
        System.out.println("Application started");

    }
}
