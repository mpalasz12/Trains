import React from "react";
import SeatElement from "../Elements/SeatElement";

function SeastPanel({train, current_wagon_index, handleSeatChange}) 
{
    return (
      <>
        {train.wagons[current_wagon_index].seats.map((seat, index) => (
            <SeatElement key={index} seat={seat} handleSeatChange={handleSeatChange}/>
        ))}
      </>
    );
}

  
export default SeastPanel;