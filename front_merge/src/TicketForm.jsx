import React, {useState} from "react";
import './TicketForm.css';

function TicketForm() {
    const [isSubmitted, setIsSubmitted] = useState(false);
    const [startStation, setStartStation] = useState('');
    const [endStation, setEndStation] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
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

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log({
            startStation,
            endStation,
            firstName,
            lastName,
            train,
            wagon,
            seat
        });
        setIsSubmitted(true);
    };

    const ticketId = 123456789

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