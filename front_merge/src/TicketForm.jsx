import React, {useState} from "react";
import './TicketForm.css';
import axios from 'axios';

function TicketForm() {
    const [isSubmitted, setIsSubmitted] = useState(false);
    const [startStation, setStartStation] = useState('');
    const [endStation, setEndStation] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [mail, setMail] = useState('');
    const [train, setTrain] = useState('');
    const [wagon, setWagon] = useState('');
    const [seat, setSeat] = useState('');

    const handleChange = (event) => {
        switch(event.target.name) {
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
            case 'train':
                setTrain(event.target.value);
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
        event.preventDefault();
        console.log({
            startStation,
            endStation,
            firstName,
            lastName,
            mail,
            train,
            wagon,
            seat
        });
    
        const travelerData = {
            first_name: firstName,
            last_name: lastName,
            mail: mail
        };
    
        try {
            await axios.post('http://localhost:8080/data/add_traveler', travelerData);
            console.log("Traveler added successfully");
        } catch (error) {
            console.error("Error adding traveler:", error);
            return;
        }
    
        try {
            const response = await axios.get("http://localhost:8080/data/get_traveler_id_by_mail", {
                params: { mail: mail }
            });
            const travelerId = response.data;
    
            const ticketData = {
                traveler_id: travelerId,
                first_stop: startStation,
                last_stop: endStation,
                train_id: train,
                wagon_num: wagon,
                seat_num: seat
            };
    
            try {
                const ticketResponse = await axios.post("http://localhost:8080/data/add_ticket", ticketData);
                console.log("Ticket added successfully");
                const ticketId = ticketResponse.data.ticket_id;
                setIsSubmitted(true);
            } catch (error) {
                console.error("Error adding ticket:", error);
            }
        } catch (error) {
            console.error("Error fetching traveler ID:", error);
        }
    
        setIsSubmitted(true);
    };

    //const ticketId = 123456789

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
                Pociąg:
                <input type="text" name="train" value={train} onChange={handleChange} />
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
        </form>
    );
}

export default TicketForm;