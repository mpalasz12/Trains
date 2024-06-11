import React from "react";
import './TicketForm.css';

function TicketForm() {
    return (
        <form>
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
        </form>
    );
}

export default TicketForm;