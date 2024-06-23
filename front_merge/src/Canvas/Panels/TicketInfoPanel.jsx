import React from "react";

function TicketInfoPanel({seat})
{
    return (
      <>
        <p>Seat: <b>{seat.number}</b></p>
        {seat.isOccupied ? (
        <>
            <p>Traveler info:</p>
            <p>name: <b>{seat.traveler.name}</b></p>
            <p>surname: <b>{seat.traveler.surname}</b></p>
            <p>Travels to: <b>{seat.traveler.end_station}</b></p>
            <p> Ticket id: <b>{seat.traveler.ticket_id}</b></p>
        </>
        ):(
            <p>Traveler: Empty</p>
        )}
      </>
    );
}

  
export default TicketInfoPanel;