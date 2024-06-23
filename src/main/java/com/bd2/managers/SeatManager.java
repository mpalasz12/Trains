package com.bd2.managers;

import dbservice.DatabaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatManager {

    private final DatabaseController database;

    @Autowired
    public SeatManager(DatabaseController database) {
        this.database = database;
    }

    public List<Integer> getSeatsTaken(int train_id, int wagon_num) {
        return database.getSeatsTaken(train_id, wagon_num);
    }
}
