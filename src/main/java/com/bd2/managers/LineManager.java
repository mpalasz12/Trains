package com.bd2.managers;

import dbservice.DatabaseController;
import dbservice.models.Line;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineManager {

    private final DatabaseController database;

    @Autowired
    public LineManager(DatabaseController database) {
        this.database = database;
    }

    public void addLine(Line line) {
        database.addLine(line);
    }

    public Line getLineByID(int id) {
        return database.getLineByID(id);
    }
}
