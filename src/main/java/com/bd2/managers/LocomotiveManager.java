package com.bd2.managers;

import dbservice.DatabaseController;
import dbservice.models.Locomotive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocomotiveManager {

    private final DatabaseController database;

    @Autowired
    public LocomotiveManager(DatabaseController database) {
        this.database = database;
    }

    public void addLocomotive(Locomotive locomotive) {
        database.addLocomotive(locomotive);
    }
}
