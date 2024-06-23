package com.bd2.managers;

import dbservice.DatabaseController;
import dbservice.models.Wagon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WagonManager {

    private final DatabaseController database;

    @Autowired
    public WagonManager(DatabaseController database) {
        this.database = database;
    }

    public void addWagon(Wagon wagon) {
        database.addWagon(wagon);
    }
}
