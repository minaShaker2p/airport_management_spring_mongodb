package com.mina.AirportManagmentMongo;

import com.mina.AirportManagmentMongo.db.repository.AirportRepository;
import com.mina.AirportManagmentMongo.db.repository.FlightInformationRepository;
import com.mina.AirportManagmentMongo.domain.Airport;
import com.mina.AirportManagmentMongo.domain.FlightInformation;
import com.mina.AirportManagmentMongo.utils.FlightPrinter;
import com.mina.AirportManagmentMongo.queries.FlightInformationQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Order(2)
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    private final FlightInformationQueries flightInformationQueries;

    @Autowired
    private FlightInformationRepository repository;

    @Autowired
    private AirportRepository airportRepository;


    @Override
    public void run(String... args) throws Exception {

        Airport rome = airportRepository.findById("1d1aab22-670b-48cb-a027-721e2055731f").get();
        rome.setName("Leonardo da vinci (Fiumicino)");
        airportRepository.save(rome);
        System.out.println("--> AFTER UPDATE TO ROME AIRPORT");
        List<FlightInformation> flights= repository.findAll();
        FlightPrinter.print(flights);



        printFlightById("5ee74c19e32cff0f3c98f776");

        delayFlight("5ee74c19e32cff0f3c98f776", 30);

        removeFlight("5ee74c19e32cff0f3c98f776");

        printByDepartureAndDestination("Bucharest", "Rome");

        printByMinNbSeats(200);

        System.out.println("-----\nQUERY :find all flights  with paging and sorting");
        List<FlightInformation> allFLightsOrdered = this.flightInformationQueries
                .findAll("departure", 0, 3);
        FlightPrinter.print(allFLightsOrdered);

        System.out.println("------------------------------------------------------ ");
        System.out.println("-----\nQUERY :find flight by id ");
        FlightInformation flightById = this.flightInformationQueries
                .findSingleById("5ee388f56c031262d01c8485");
        System.out.println(flightById);


        System.out.println("-c----------------------------------------------------- \n");
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

    private void removeFlight(String id) {
        repository.deleteById(id);
        System.out.println("Deleted Flight " + id);
    }

    private void delayFlight(String id, int duration) {

        if(repository.findById(id).isPresent())
        {
            // first find flight
            FlightInformation flight = repository.findById(id).get();
            // update flight  duration info
            flight.setDurationMin(flight.getDurationMin() + duration);

            // then save the updated flight
            repository.save(flight);

            System.out.println("Updated flight " + id + "\n");
        }

    }

    /**
     * Method which print flight information based on specific id
     *
     * @param id
     */
    private void printFlightById(String id) {
        System.out.println("Flight " + id);
        if (repository.findById(id).isPresent()) {
            FlightInformation flight = repository.findById(id).get();
            FlightPrinter.print(Arrays.asList(flight));
        }
    }


    private void printByDepartureAndDestination(String departure, String destination) {
        System.out.println("Fights From " + departure + " to " + destination);

        List<FlightInformation> flights = repository.findByDepartureCityAndDestinationCity(departure, destination);

        FlightPrinter.print(flights);
    }

    private void printByMinNbSeats(int minNbSeats) {
        System.out.println("Flights by min nb seats " + minNbSeats);

        List<FlightInformation> flights = repository.findByMinAircraftNbSeats(minNbSeats);
        FlightPrinter.print(flights);
    }
}
