package com.bd2.managers;

import dbservice.DatabaseController;
import dbservice.models.Linestop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinestopManager {

    private final DatabaseController database;

    @Autowired
    public LinestopManager(DatabaseController database) {
        this.database = database;
    }

    public void addLinestop(Linestop linestop) {
        linestop.setID(database.addLinestop(linestop));
    }

    public void addTerminus(Linestop linestop) {
        linestop.setID(database.addTerminus(linestop));
    }
}
