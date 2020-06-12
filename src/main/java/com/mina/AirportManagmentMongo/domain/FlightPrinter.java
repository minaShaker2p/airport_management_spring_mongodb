package com.mina.AirportManagmentMongo.domain;

import java.util.List;

public class FlightPrinter {

    public static void print(List<FlightInformation> flights) {
        flights.forEach(System.out::println);
    }
}
