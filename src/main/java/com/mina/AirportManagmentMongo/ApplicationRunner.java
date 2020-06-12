package com.mina.AirportManagmentMongo;

import com.mina.AirportManagmentMongo.domain.FlightInformation;
import com.mina.AirportManagmentMongo.domain.FlightPrinter;
import com.mina.AirportManagmentMongo.queries.FlightInformationQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    private final FlightInformationQueries flightInformationQueries;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("-----\nQUERY :find all flights  with paging and sorting");
        List<FlightInformation> allFLightsOrdered = this.flightInformationQueries
                .findAll("departure", 0, 3);
        FlightPrinter.print(allFLightsOrdered);

        System.out.println("------------------------------------------------------ ");
        System.out.println("-----\nQUERY :find flight by id ");
        FlightInformation flightById = this.flightInformationQueries
                .findSingleById("5ee388f56c031262d01c8485");
        System.out.println(flightById);


        System.out.println("------------------------------------------------------ \n");
        System.out.println("QUERY :Count all international flight ");
        Long count = this.flightInformationQueries
                .countAllInternational();
        System.out.println(count);


        System.out.println("------------------------------------------------------ \n");
        System.out.println("QUERY :find all flights by duration between min and max ");
        List<FlightInformation> flightsByDuration = this.flightInformationQueries.findByDurationMinAndMax(2, 4);
        FlightPrinter.print(flightsByDuration);

        System.out.println("------------------------------------------------------ \n");
        System.out.println("QUERY :find all flights by departure cit ");
        List<FlightInformation> flightsByDeparture = this.flightInformationQueries.findByDeparture("Leipzig");
        FlightPrinter.print(flightsByDeparture);

        System.out.println("------------------------------------------------------ \n");
        System.out.println("QUERY :find all flights delayed at a particular departure departure city ");
        List<FlightInformation> flightsDelayedByDeparture = this.flightInformationQueries.findDelayedAtDeparture("London");
        FlightPrinter.print(flightsDelayedByDeparture);

        System.out.println("------------------------------------------------------ \n");
        System.out.println("QUERY :find all flights that on time  and relate to a city");
        List<FlightInformation> flightsAtTimeByDeparture = this.flightInformationQueries.findRelatedToCityAndNotDelayed("Leipzig");
        FlightPrinter.print(flightsAtTimeByDeparture);


    }
}
