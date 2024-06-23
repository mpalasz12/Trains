import React from "react";
import WagonBtn from "../Elements/WagonBtn";

function WagonsPanel({ currentTrain, focusOnWagonClick, current_wagon_index}) {
    
    const handleWagonClick = (wagon_index) =>
    {
        focusOnWagonClick(wagon_index);
        console.log("View changed to wagon: ", wagon_index);
    };

    return (
        <>
        {currentTrain.wagons.map((wagon, index) =>(
            <WagonBtn key={index} isFocused={index===current_wagon_index} handleWagonClick={handleWagonClick} wagon_number={index}/>
        ))}
        </>
    );
  }
    
  export default WagonsPanel;