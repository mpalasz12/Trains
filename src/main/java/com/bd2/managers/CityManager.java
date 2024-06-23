package com.bd2.managers;

import dbservice.DatabaseController;
import dbservice.models.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityManager {

    private final DatabaseController database;

    @Autowired
    public CityManager(DatabaseController database) {
        this.database = database;
    }

    public String getCity(int id) {
        try {
            return database.getCity(id);
        } catch (Exception e) {
            return null;
        }
    }

    public City getCityByName(String name) {
        return database.getCityByName(name);
    }

    public void addCity(City city) {
        database.addCity(city);
    }
    
    public List<Station> getStationsByCityId(int cityId) {
        return database.getStationsByCityId(cityId);
    }
}
