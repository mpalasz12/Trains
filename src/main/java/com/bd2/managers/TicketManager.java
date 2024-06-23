package com.bd2.managers;

import dbservice.DatabaseController;
import dbservice.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketManager {

    private final DatabaseController database;

    @Autowired
    public TicketManager(DatabaseController database) {
        this.database = database;
    }

    public List<Ticket> getTicketsByMail(String mail) {
        return database.getTicketsByTravelerID(database.getTravelerIDbyMail(mail));
    }

    public void addTicket(Ticket ticket) {
        ticket.setTicket_id(database.addTicket(ticket));
    }
}
