import React, {useState} from "react";
import './TicketForm.css';

function TicketForm() {
    const [isSubmitted, setIsSubmitted] = useState(false);

    const handleSubmit = (event) => {
        event.preventDefault();
        // TODO pobranie danych i zainicjowanie biletu w bazie danych
        //      + wyświetlnenie ID kupionego biletu
        setIsSubmitted(true);
    };

    const ticketId = 123456789

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Stacja początkowa:
                <input type="text" name="startStation" />
            </label>
            <label>
                Stacja końcowa:
                <input type="text" name="endStation" />
            </label>
            <label>
                Imię:
                <input type="text" name="firstName" />
            </label>
            <label>
                Nazwisko:
                <input type="text" name="lastName" />
            </label>
            <label>
                Pociąg:
                <input type="text" name="train" />
            </label>
            <label>
                Wagon:
                <input type="number" name="wagon" />
            </label>
            <label>
                Miejsce:
                <input type="number" name="seat" />
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