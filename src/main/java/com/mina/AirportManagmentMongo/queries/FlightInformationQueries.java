package com.mina.AirportManagmentMongo.queries;

import com.mina.AirportManagmentMongo.domain.FlightInformation;
import com.mina.AirportManagmentMongo.domain.FlightType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FlightInformationQueries {

    @Autowired
    private final MongoTemplate mongoTemplate;
    /*
    TODO : implement all following queries

    1) find all flights  with paging and sorting.
    2) find flight by id
    3) count all international flights
    4) find all flights by departure city.
    5) find all flights by duration between min and max
    6) find all flights delayed at a particular departure departure city
    7) find all flights that on time  and relate to a city.
    8) find all aircraft model
     */


    // find all flights  with paging and sorting.

    public List<FlightInformation> findAll(String field, int pageNum, int pageSize) {
        Query allPagedAndOrdered = new Query()
                .with(Sort.by(Sort.Direction.ASC, field))
                .with(PageRequest.of(pageNum, pageSize));
        return mongoTemplate.find(allPagedAndOrdered, FlightInformation.class);
    }

    // find flight by id

    public FlightInformation findSingleById(String id) {
        return mongoTemplate.findById(id, FlightInformation.class);
    }

    //  3) count all international flights

    public Long countAllInternational() {
        Query allInternational = Query.query(Criteria.where("flightType")
                .is(FlightType.International));
        return this.mongoTemplate.count(allInternational, FlightInformation.class);
    }

    //4) find all flights by departure city.

    public List<FlightInformation> findByDeparture(String departure) {

        Query byDeparture = Query.query(Criteria.where("departure.city")
                .is(departure));

        return this.mongoTemplate.find(byDeparture, FlightInformation.class);
    }

    //5) find all flights by duration between min and max

    public List<FlightInformation> findByDurationMinAndMax(int minMinutes, int maxMinutes) {
        Query byDurationMinAndMax = Query.query(Criteria.where("duration")
                .gte(minMinutes)
                .lte(maxMinutes));

        return this.mongoTemplate.find(byDurationMinAndMax, FlightInformation.class);
    }

    //  6) find all flights delayed at a particular departure departure city

    public List<FlightInformation> findDelayedAtDeparture(String departure) {
        Query delayedAtDeparture = Query.query(Criteria.where("isDelayed")
                .is(true).andOperator(Criteria.where("departure.city").is(departure)));
        return mongoTemplate.find(delayedAtDeparture, FlightInformation.class);
    }

    //  7) find all flights that on time  and relate to a city.
    public List<FlightInformation> findRelatedToCityAndNotDelayed(String city) {
        Query byCity = Query.query(new Criteria().orOperator(Criteria.where("departure.city").is(city),
                Criteria.where("destination.city").is(city))
                .andOperator(Criteria.where("isDelayed").is(false)));
        return this.mongoTemplate.find(byCity, FlightInformation.class);
    }

    //8) find all aircraft model
    public List<FlightInformation> findByAirCraft(String aircraft) {
        Query byAircraft =Query.query(Criteria.where("aircraft.model").is(aircraft));
        return  this.mongoTemplate.find(byAircraft,FlightInformation.class);
    }
}
