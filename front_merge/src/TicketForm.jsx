import React, { useState, useEffect } from "react";
import './TicketForm.css';
import axios from 'axios';
import './App.jsx';

function TicketForm() {
    const [isSubmitted, setIsSubmitted] = useState(false);
    const [firstStationInDataBase, setFirstStationInDataBase] = useState(true);
    const [lastStationInDataBase, setLastStationInDataBase] = useState(true);
    const [noDirectRoute, setNoDirectRoute] = useState(false);
    const [isSeatTaken, setIsSeatTaken] = useState(false);
    const [tooHighWagon, setTooHighWagon] = useState(false);
    const [tooHighSeat, setTooHighSeat] = useState(false);
    const [notAllInformationGiven, setNotAllInformationGiven] = useState(false);
    const [invalidConnection, setInvalidConnection] = useState(false);
    const [startStation, setStartStation] = useState('');
    const [endStation, setEndStation] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [mail, setMail] = useState('');
    //const [trainId, setTrainId] = useState('');
    const [wagon, setWagon] = useState('');
    const [seat, setSeat] = useState('');
    const [ticketId, setTicketId] = useState('');

    const handleChange = (event) => {
        const { name, value } = event.target;
        switch (name) {
            case 'startStation':
                setStartStation(value);
                break;
            case 'endStation':
                setEndStation(value);
                break;
            case 'firstName':
                setFirstName(value);
                break;
            case 'lastName':
                setLastName(value);
                break;
            case 'mail':
                setMail(value);
                break;
            case 'wagon':
                setWagon(value);
                break;
            case 'seat':
                setSeat(value);
                break;
            default:
                break;
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        setIsSubmitted(false);
        setNoDirectRoute(false);
        setIsSeatTaken(false);
        setTooHighWagon(false);
        setTooHighSeat(false);
        setNotAllInformationGiven(false);
        setInvalidConnection(false);

        console.log("zaczynam PRROOOOOGRRRRAAAAMMMMMM");

        if (firstName == '' || lastName == '' || startStation == '' || endStation == '' || mail == '' || wagon == '' || seat == '') {
            setNotAllInformationGiven(true);
            console.error("You didn't fill all your information name box.");
            return;
        }

        if (startStation == endStation) {
            setInvalidConnection(true);
            return
        }

        try {
            const response = await axios.get("http://localhost:8080/data/all_stations");
            const allStations = response.data;
            const stationNames = allStations.map(station => station.name);
            setFirstStationInDataBase(stationNames.includes(startStation));
            setLastStationInDataBase(stationNames.includes(endStation));

            if (!stationNames.includes(startStation) || !stationNames.includes(endStation)) {
                console.error("We don't have these stations in our database");
                return;
            }
        } catch (error) {
            console.error("Error loading all available stations in our database", error);
            return;
        }

        let travelerID;
        try {
            const response = await axios.get("http://localhost:8080/data/get_traveler_id_by_mail", {
                params: { mail: mail }
            });
            travelerID = response.data;
            console.log(travelerID);
        } catch (error) {
            console.log("Traveler not found, adding new traveler...");
            try {
                await axios.post('http://localhost:8080/data/add_traveler', null, {
                    params: {
                        first_name: firstName,
                        last_name: lastName,
                        mail: mail
                    }
                });
                console.log("Traveler added successfully");
                const response = await axios.get("http://localhost:8080/data/get_traveler_id_by_mail", {
                    params: { mail: mail }
                });
                travelerID = response.data;
                console.log(travelerID);
            } catch (error) {
                console.error("Error adding traveler or fetching traveler ID:", error);
                return;
            }
        }

        let lineData;
        try {
            const response = await axios.get('http://localhost:8080/data/find_connection', {
                params: {
                    start_name: startStation,
                    end_name: endStation,
                }
            });
            lineData = response.data;
            if (!lineData || lineData.length === 0) {
                setNoDirectRoute(true);
                return;
            }
            console.log(lineData);
        } catch (error) {
            console.error("Error getting line:", error);
            setNoDirectRoute(true);
            return;
        }

        let foundTrainId;
        try {
            const response = await axios.get("http://localhost:8080/data/get_all_trains");
            const trainsData = response.data;
            for (let i = 0; i < trainsData.length; i++) {
                if (trainsData[i].line_id === lineData[0]) {
                    foundTrainId = trainsData[i].train_id;
                    break;
                }
            }
            if (!foundTrainId) {
                console.error("Train ID not found");
                return;
            }
            console.log("Found train ID:", foundTrainId);
        } catch (error) {
            console.error("Error getting train_id from line_id:", error);
            return;
        }
        //setTrainId(foundTrainId);

        try {
            const response = await axios.get("http://localhost:8080/data/get_train_wagons", {
                params: {
                    train_id: foundTrainId,
                }
            });
            const trainsDataWagons = response.data;
            if (wagon > trainsDataWagons.length) {
                setTooHighWagon(true);
                return;
            }
            if (seat > trainsDataWagons[wagon - 1].wagon_capacity) {
                setTooHighSeat(true);
                return;
            }
        } catch (error) {
            console.error("Error getting train wagons or invalid seat number:", error);
            return;
        }

        try {
            const response = await axios.get("http://localhost:8080/data/get_seats_taken", {
                params: {
                    train_id: foundTrainId,
                    wagon_num: wagon
                }
            });
            const takenSeats = response.data;
            if (takenSeats.includes(parseInt(seat))) {
                setIsSeatTaken(true);
                return;
            }
        } catch (error) {
            console.error("Error checking seat availability:", error);
            return;
        }

        try {
            const response = await axios.post('http://localhost:8080/data/add_ticket', null, {
                params: {
                    traveler_id: travelerID,
                    first_stop: lineData[1],
                    last_stop: lineData[2],
                    train_id: foundTrainId,
                    wagon_num: wagon,
                    seat_num: seat
                }
            });
            console.log("Ticket added successfully");
            // refreshTrainData();  ODKOMENTOWAĆ JAK BĘDZIE GOTOWE
            const ticketResponse = await axios.get('http://localhost:8080/data/tickets_by_mail', {
                params: { mail: mail }
            });
            const tickets = ticketResponse.data;
            setTicketId(tickets[tickets.length - 1].ticket_id);
            setIsSubmitted(true);
        } catch (error) {
            console.error("Error adding ticket or fetching ticket ID:", error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Stacja początkowa:
                <input type="text" name="startStation" value={startStation} onChange={handleChange} />
            </label>
            <label>
                Stacja końcowa:
                <input type="text" name="endStation" value={endStation} onChange={handleChange} />
            </label>
            <label>
                Imię:
                <input type="text" name="firstName" value={firstName} onChange={handleChange} />
            </label>
            <label>
                Nazwisko:
                <input type="text" name="lastName" value={lastName} onChange={handleChange} />
            </label>
            <label>
                Mail:
                <input type="text" name="mail" value={mail} onChange={handleChange} />
            </label>
            <label>
                Wagon:
                <input type="number" name="wagon" value={wagon} onChange={handleChange} />
            </label>
            <label>
                Miejsce:
                <input type="number" name="seat" value={seat} onChange={handleChange} />
            </label>
            <input type="submit" value="Kup" />
            {isSubmitted && (
                <div className="ticket-id-container">
                    <span className="ticket-id-label">Id kupionego biletu:</span>
                    <span className="ticket-id">{ticketId}</span>
                </div>
            )}
            {noDirectRoute && (
                <div className="error-message">
                    <span>Nie znaleziono trasy bezpośredniej.</span>
                </div>
            )}
            {isSeatTaken && (
                <div className="error-message">
                    <span>Wybrane miejsce jest już zajęte.</span>
                </div>
            )}
            {tooHighWagon && (
                <div className="error-message">
                    <span>Wybrany numer wagonu nie istnieje.</span>
                </div>
            )}
            {tooHighSeat && (
                <div className="error-message">
                    <span>Wybrany numer miejsca nie istnieje w tym wagonie.</span>
                </div>
            )}
            {!firstStationInDataBase && (
                <div className="error-message">
                    <span>Wybrana stacja początkowa nie jest w naszym systemie.</span>
                </div>
            )}
            {!lastStationInDataBase && (
                <div className="error-message">
                    <span>Wybrana stacja końcowa nie jest w naszym systemie.</span>
                </div>
            )}
            {notAllInformationGiven && (
                <div className="error-message">
                    <span>Wszystkie pola z danymi nie mogą być puste.</span>
                </div>
            )}
            {invalidConnection && (
                <div className="error-message">
                    <span>Stacja końcowa nie moze być taka sama jak stacja początkowa.</span>
                </div>
            )}
        </form>
    );
}

export default TicketForm;
