import React, { useState } from "react";
import './TicketCheck.css';
import axios from 'axios';

function TicketCheck() {
    const [isSubmitted, setIsSubmitted] = useState(false);
    const [mail, setMail] = useState('');
    const [ticketData, setTicketData] = useState(null);
    const [travelerData, setTravelerData] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            console.log(mail);
            const response = await axios.get("http://localhost:8080/tickets_by_mail", null, {
                params: {
                    mail: mail
                }
            });
            console.log("Ticket checked successfully");
            setTicketData(response.data);
            setIsSubmitted(true);
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
            {isSubmitted && ticketData && (
                <div className="ticket-details">
                    <div className="info-container">
                        <span className="info-label">ID Biletu:</span>
                        <span className="info">{ticketData.ticket_id}</span>
                    </div>
                    <div className="info-container">
                        <span className="info-label">Stacja początkowa:</span>
                        <span className="info">{ticketData.first_stop}</span>
                    </div>
                    <div className="info-container">
                        <span className="info-label">Stacja końcowa:</span>
                        <span className="info">{ticketData.last_stop}</span>
                    </div>



                    <div className="info-container">
                        <span className="info-label">Pociąg:</span>
                        <span className="info">{ticketData.train_id}</span>
                    </div>
                    <div className="info-container">
                        <span className="info-label">Wagon:</span>
                        <span className="info">{ticketData.wagon_num}</span>
                    </div>
                    <div className="info-container">
                        <span className="info-label">Miejsce:</span>
                        <span className="info">{ticketData.seat_num}</span>
                    </div>
                    <div className="info-container">
                        <span className="info-label">Czy niewazny:</span>
                        <span className="info">{ticketData.is_expired}</span>
                    </div>
                </div>
            )}
        </form>
    );
}

export default TicketCheck;

/*
                    <div className="info-container">
                        <span className="info-label">Imię:</span>
                        <span className="info">{ticketData.firstName}</span>
                    </div>
                    <div className="info-container">
                        <span className="info-label">Nazwisko:</span>
                        <span className="info">{ticketData.lastName}</span>
                    </div>
*/