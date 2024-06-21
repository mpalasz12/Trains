import React, { useState } from "react";
import './TicketCheck.css';

function TicketCheck() {
    const [isSubmitted, setIsSubmitted] = useState(false);
    const [ticketId, setTicketId] = useState('');
    const [ticketData, setTicketData] = useState(null);

    const handleSubmit = (event) => {
        event.preventDefault();
		// TODO pobranie danych z biletu
        const fetchedTicketData = {
            id: ticketId,
            startStation: 'Warszawa',
            endStation: 'Kraków',
            firstName: 'Jan',
            lastName: 'Kowalski',
            train: 'IC1234',
            wagon: 5,
            seat: 12
        };
        setTicketData(fetchedTicketData);
        setIsSubmitted(true);
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Podaj ID biletu: 
                <input
                    type="text"
                    value={ticketId}
                    onChange={(e) => setTicketId(e.target.value)}
                />
            </label>
            <input type="submit" value="Sprawdź" />
            {isSubmitted && ticketData && (
                <div className="ticket-details">
                    <div className="info-container">
                        <span className="info-label">ID Biletu:</span>
                        <span className="info">{ticketData.id}</span>
                    </div>
                    <div className="info-container">
                        <span className="info-label">Stacja początkowa:</span>
                        <span className="info">{ticketData.startStation}</span>
                    </div>
                    <div className="info-container">
                        <span className="info-label">Stacja końcowa:</span>
                        <span className="info">{ticketData.endStation}</span>
                    </div>
                    <div className="info-container">
                        <span className="info-label">Imię:</span>
                        <span className="info">{ticketData.firstName}</span>
                    </div>
                    <div className="info-container">
                        <span className="info-label">Nazwisko:</span>
                        <span className="info">{ticketData.lastName}</span>
                    </div>
                    <div className="info-container">
                        <span className="info-label">Pociąg:</span>
                        <span className="info">{ticketData.train}</span>
                    </div>
                    <div className="info-container">
                        <span className="info-label">Wagon:</span>
                        <span className="info">{ticketData.wagon}</span>
                    </div>
                    <div className="info-container">
                        <span className="info-label">Miejsce:</span>
                        <span className="info">{ticketData.seat}</span>
                    </div>
                </div>
            )}
        </form>
    );
}

export default TicketCheck;