package com.mina.AirportManagmentMongo;

import com.mina.AirportManagmentMongo.db.GenericCascadeListener;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@TestConfiguration
public class DatabaseTestConfiguration {

    @Bean
    GenericCascadeListener genericCascadeListener(MongoTemplate mongoTemplate) {
        return new GenericCascadeListener(mongoTemplate);
    }
}
