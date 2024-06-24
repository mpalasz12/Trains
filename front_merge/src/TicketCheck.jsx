import React, { useState } from "react";
import './TicketCheck.css';
import axios from 'axios';

function TicketCheck() {
    const [isSubmitted, setIsSubmitted] = useState(false);
    const [mail, setMail] = useState('');
    const [ticketsData, setTicketsData] = useState([]);
    const [stationsNames, setStationsNames] = useState({});

    const fetchStationName = async (linestopId) => {
        try {
            const response = await axios.get("http://localhost:8080/data/get_station_by_linestop_id", {
                params: { linestop_id: linestopId }
            });
            return response.data.name;
        } catch (error) {
            console.error(`Error fetching station name for linestop ID ${linestopId}`, error);
            return null;
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await axios.get("http://localhost:8080/data/tickets_by_mail", {
                params: { mail }
            });
            const tickets = response.data;
            setTicketsData(tickets);
            setIsSubmitted(true);

            const stations = await Promise.all(tickets.map(async (ticket) => {
                const firstStopName = await fetchStationName(ticket.first_stop);
                const lastStopName = await fetchStationName(ticket.last_stop);
                return { ticketId: ticket.ticket_id, firstStopName, lastStopName };
            }));

            const stationsMap = stations.reduce((acc, station) => {
                acc[station.ticketId] = station;
                return acc;
            }, {});

            setStationsNames(stationsMap);
        } catch (error) {
            console.error('There was an error in TicketCheck!', error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Podaj maila:
                <input
                    type="text"
                    value={mail}
                    onChange={(e) => setMail(e.target.value)}
                />
            </label>
            <input type="submit" value="Sprawdź" />
            {isSubmitted && ticketsData.length > 0 && (
                <div className="tickets-details">
                    {ticketsData.map(ticket => (
                        <div key={ticket.ticket_id} className="ticket-details">
                            <div className="info-container">
                                <span className="info-label">ID Biletu:</span>
                                <span className="info">{ticket.ticket_id}</span>
                            </div>
                            <div className="info-container">
                                <span className="info-label">Stacja początkowa:</span>
                                <span className="info">{stationsNames[ticket.ticket_id]?.firstStopName}</span>
                            </div>
                            <div className="info-container">
                                <span className="info-label">Stacja końcowa:</span>
                                <span className="info">{stationsNames[ticket.ticket_id]?.lastStopName}</span>
                            </div>
                            <div className="info-container">
                                <span className="info-label">Pociąg:</span>
                                <span className="info">{ticket.train_id}</span>
                            </div>
                            <div className="info-container">
                                <span className="info-label">Wagon:</span>
                                <span className="info">{ticket.wagon_num}</span>
                            </div>
                            <div className="info-container">
                                <span className="info-label">Miejsce:</span>
                                <span className="info">{ticket.seat_num}</span>
                            </div>
                            <div className="info-container">
                                <span className="info-label">Czy bilet jest wazny:</span>
                                <span className="info">{ticket.is_expired ? "Nie" : "Tak"}</span>
                            </div>
                        </div>
                    ))}
                </div>
            )}
        </form>
    );
}

export default TicketCheck;
