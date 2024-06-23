import React from "react";

function SeatElement({seat, handleSeatChange}) {
    return (
        <>
        {seat.isOccupied ? (
            <button className="seatElementOccupied" onClick={() => handleSeatChange(seat.number-1)}>
                <p className="seatFont">{seat.number}</p>
            </button>
        ) : (
            <button className="seatElementFree" onClick={() => handleSeatChange(seat.number-1)}>
                <b><p className="seatFont">{seat.number}</p></b>
            </button>
        )}
        </>
    );
}

export default SeatElement;