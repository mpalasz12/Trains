import WagonsPanel from "./WagonsPanel";
import SeastPanel from './SeatsPanel';
import LocomotiveBtn from "../Elements/LocomotiveBtn";
import WagonInfoPanel from "./WagonInfoPanel";
import TicketInfoPanel from "./TicketInfoPanel";
import TrackInfoPanel from "./TrackInfoPanel";
import React from "react";

function BottomPanel({ currentTrain, handleWagonChange, current_wagon_index, handleSeatChange, current_seat_index}) {
  
  const handleLocomotiveClick = () =>
  {
    handleWagonChange(-1);
  };
  
  return (
    <>
      <div className="bottom_separator">
        <div className="train_struct_display">
          <LocomotiveBtn isFocused={current_wagon_index===-1} currentLocomotive={currentTrain.locomotive} handleLocomotiveClick={handleLocomotiveClick}/>
          <div className="wagons_panel">
            <WagonsPanel currentTrain={currentTrain} focusOnWagonClick={handleWagonChange} current_wagon_index={current_wagon_index}/>
          </div>
        </div>
        <div className="wagon_info">
          {(current_wagon_index === -1) ? (
            <WagonInfoPanel train={currentTrain}/>
          ):(
            <div className="grid_seats">
              <SeastPanel train={currentTrain} current_wagon_index={current_wagon_index} handleSeatChange={handleSeatChange}/>
            </div>
          )}
        </div>
      </div>
      <div className="ticket_info">
        {current_seat_index >= 0 ? (
          <TicketInfoPanel seat={currentTrain.wagons[current_wagon_index].seats[current_seat_index]}/>
          ) : (
          <TrackInfoPanel track={currentTrain.track}/>
        )}
      </div>
    </>
  );
}
  
export default BottomPanel;