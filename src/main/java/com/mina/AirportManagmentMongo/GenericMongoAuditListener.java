package com.mina.AirportManagmentMongo;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class GenericMongoAuditListener extends AbstractMongoEventListener<Object> {

    @Override
    public void onAfterSave(AfterSaveEvent<Object> event) {
        Object document = event.getSource();
        System.out.println(LocalDateTime.now() + " Saved document " + document);
    }

    @Override
    public void onBeforeSave(BeforeSaveEvent<Object> event) {
        Object document = event.getSource();
        System.out.println(LocalDateTime.now() + " Deleted document " + document);
    }
}
