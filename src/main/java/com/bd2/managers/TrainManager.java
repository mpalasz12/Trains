package com.bd2.managers;

import dbservice.DatabaseController;
import dbservice.models.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainManager {

    private final DatabaseController database;

    @Autowired
    public TrainManager(DatabaseController database) {
        this.database = database;
    }

    public void addTrain(Train train) {
        database.addTrain(train);
    }

    public List<Train> getActiveTrains() {
        return database.getActiveTrains();
    }

    public Train getTrainByID(int id) {
        return database.getTrainByID(id);
    }

    public void advanceTrain(int trainId) {
        database.advanceTrain(trainId);
    }
}
