import React from "react";
import TrainBtn from "../Elements/TrainBtn";

function TrainsPanel({trainManager, focusOnTrainClick, current_train_index}) 
{
    return (
      <>
        <div className="trains_header"> Active Trains </div>
        {trainManager.trains.map((train, index) =>(
          <TrainBtn key={index} handleTrainClick={focusOnTrainClick} train={train} index={index} isFocused={trainManager.trains[current_train_index] === train}/>
        ))}
      </>
    );
}

  
export default TrainsPanel;