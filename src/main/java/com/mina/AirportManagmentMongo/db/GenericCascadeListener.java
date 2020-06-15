package com.mina.AirportManagmentMongo.db;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
@AllArgsConstructor
public class GenericCascadeListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private final MongoTemplate mongoTemplate;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object document = event.getSource();
        ReflectionUtils.doWithFields(document.getClass(), docField -> {
            ReflectionUtils.makeAccessible(docField);
            if (docField.isAnnotationPresent(DBRef.class)) {
                final Object fieldValue = docField.get(document);
                mongoTemplate.save(fieldValue);
            }
        });

    }
}
