package com.bd2.managers;

import dbservice.DatabaseController;
import dbservice.models.Traveler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TravelerManager {

    private final DatabaseController database;

    @Autowired
    public TravelerManager(DatabaseController database) {
        this.database = database;
    }

    public void addTraveler(Traveler traveler) {
        database.addTraveler(traveler);
    }

    public Integer getTravelerIDbyMail(String mail) {
        return database.getTravelerIDbyMail(mail);
    }
}
