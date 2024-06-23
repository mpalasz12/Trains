package com.bd2.managers;

import dbservice.DatabaseController;
import dbservice.models.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationManager {

    private final DatabaseController database;

    @Autowired
    public StationManager(DatabaseController database) {
        this.database = database;
    }

    public Station getStation(int id) {
        try {
            return database.getStation(id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Station> getAllStations() {
        return database.getAllStations();
    }

    public void addStation(Station station) {
        database.addStation(station);
    }
}
