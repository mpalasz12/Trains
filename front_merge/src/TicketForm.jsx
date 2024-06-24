import React, { useState } from "react";
import './TicketForm.css';
import axios from 'axios';

function TicketForm() {
    const [isSubmitted, setIsSubmitted] = useState(false);
    const [wrongInput, setWrongInput] = useState(false);
    const [noDirectRoute, setNoDirectRoute] = useState(false);
    const [isSeatTaken, setIsSeatTaken] = useState(false);
    const [startStation, setStartStation] = useState('');
    const [endStation, setEndStation] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [mail, setMail] = useState('');
    //const [train, setTrain] = useState('');
    const [wagon, setWagon] = useState('');
    const [seat, setSeat] = useState('');
    const [ticket_id, setTicketId] = useState('');
    //const [lineData, setLineData] = useState('');
    //const [traveler_id, setTravelerID] = useState('');

    const handleChange = (event) => {
        switch (event.target.name) {
            case 'startStation':
                setStartStation(event.target.value);
                break;
            case 'endStation':
                setEndStation(event.target.value);
                break;
            case 'firstName':
                setFirstName(event.target.value);
                break;
            case 'lastName':
                setLastName(event.target.value);
                break;
            case 'mail':
                setMail(event.target.value);
                break;
            case 'wagon':
                setWagon(event.target.value);
                break;
            case 'seat':
                setSeat(event.target.value);
                break;
            default:
                break;
        }
    };

    const handleSubmit = async (event) => {
        setNoDirectRoute(false);
        setIsSeatTaken(false);
        event.preventDefault();

        let travelerID;

        // Check if traveler with the same mail is already registered
        try {
            const response = await axios.get("http://localhost:8080/data/get_traveler_id_by_mail", {
                params: { mail: mail }
            });
            travelerID = response.data;
            console.log(travelerID);
        } catch (error) {
            console.log("Traveler not found, adding new traveler...");

            // Add traveler
            try {
                await axios.post('http://localhost:8080/data/add_traveler', null, {
                    params: {
                        first_name: firstName,
                        last_name: lastName,
                        mail: mail
                    }
                });
                console.log("Traveler added successfully");

                // Get traveler_id by mail again
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

        // Get connection by start_name and end_name
        let lineData;
        try {
            console.log("jestem w get connection");
            const response = await axios.get('http://localhost:8080/data/find_connection', {
                params: {
                    start_name: startStation,
                    end_name: endStation,
                }
            });
            lineData = response.data;
            if (!lineData) {
                console.error("Empty line:", error);
                setNoDirectRoute(true);
                return;
            }
            console.log(response.data);
            console.log(lineData);
            console.log("LineData added successfully");
        } catch (error) {
            console.error("Error getting line:", error);
            setNoDirectRoute(true);
            return;
        }

        // Check if the seat is already taken
        try {
            const response = await axios.get("http://localhost:8080/data/get_seats_taken", {
                params: {
                    train_id: lineData[0],
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

        // Add ticket
        try {
            console.log("jestem w add ticket");
            const response = await axios.post('http://localhost:8080/data/add_ticket', null, {
                params: {
                    traveler_id: travelerID,
                    first_stop: lineData[1],
                    last_stop: lineData[2],
                    train_id: lineData[0],
                    wagon_num: wagon,
                    seat_num: seat
                }
            });
            console.log("Ticket added successfully");

            // Fetch ticket ID
            console.log("jestem w get ticketID");
            const ticketResponse = await axios.get('http://localhost:8080/data/tickets_by_mail', {
                params: { mail: mail }
            });
            console.log(ticketResponse.data);
            console.log(ticketResponse.data.ticket_id);

            setTicketId(ticketResponse.data[ticketResponse.data.length - 1].ticket_id);
            console.log("TicketID fetched successfully");

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
                    <span className="ticket-id">{ticket_id}</span>
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
        </form>
    );
}

export default TicketForm;
