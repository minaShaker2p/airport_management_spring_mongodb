package com.mina.AirportManagmentMongo.db.converter;

import com.mina.AirportManagmentMongo.domain.Aircraft;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

public class AircraftDbReadConverter implements Converter<String, Aircraft> {


    @Override
    public Aircraft convert(String s) {
        if (s == null)
            return null;
        String[] parts = s.split("/");
        Aircraft aircraft = new Aircraft(parts[0], Integer.parseInt(parts[1]));
        return aircraft;
    }



}
