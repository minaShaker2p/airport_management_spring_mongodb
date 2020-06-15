package com.mina.AirportManagmentMongo.utils;

import com.mina.AirportManagmentMongo.domain.FlightInformation;

import java.util.List;

public class FlightPrinter {
    public FlightPrinter() {
    }

    public static void print(List<FlightInformation> flights){
        String header = String.format("%-30s %-30s %-8s %-13s %-9s %-8s",
                "DEP","DST","DURATION","DATE","DELAYED","A. TYPE");
        System.out.println(header);
        for (FlightInformation f: flights) {
            String data = String.format("%-30s %-30s %-8s %-13s %-9s %-8s",
                    f.getDepartureCity().getName(),
                    f.getDestinationCity().getName(),
                    f.getDurationMin(),
                    f.getDepartureDate(),
                    f.isDelayed(),
                    f.getAircraft().getModel());
            System.out.println(data);
        }
        System.out.println();
    }
}
