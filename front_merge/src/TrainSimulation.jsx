import React, { useState } from 'react';
import CanvasPanel from "./Canvas/Panels/CanvasPanel"
import TrainsPanel from "./Canvas/Panels/TrainsPanel"
import BottomPanel from "./Canvas/Panels/BottomPanel"
import TrainManager from "./Canvas/Logic/TrainManager"
import StationsManager from './Canvas/Logic/StationsManager';
import ConnectionsManager from './Canvas/Logic/ConnectionsManager';
import TracksManager from './Canvas/Logic/TracksManager';

function TrainSimulation() {
  const [_, refreshCanvas] = useState(0);
  const [stationsManager] = useState(new StationsManager());
  const [connectionsManager] = useState(new ConnectionsManager(stationsManager));
  const [tracksManager] = useState(new TracksManager(stationsManager, () => refreshCanvas(prev => prev + 1)));
  const [trainManager] = useState(new TrainManager(tracksManager, () => refreshCanvas(prev => prev + 1)));
  const [current_train_index, setTrain] = useState(0);
  const [current_wagon_index, setWagon] = useState(-1);
  const [current_seat_index, setSeat] = useState(-1);

  const handleTrainChange = train_index =>{
    setTrain(train_index);
    setWagon(-1);
    setSeat(-1);
  }
  const handleWagonChange = wagon_index =>{
    setWagon(wagon_index);
    setSeat(-1);
  }

  const handleSeatChange = seat_index =>{
    setSeat(seat_index);
  }


  return(
    <div className="trainSimulation">
        <div className="left_panel">
          <CanvasPanel stationsManager={stationsManager} connectionsManager={connectionsManager} trainManager={trainManager} current_train_index={current_train_index}/>
        </div>
        <div className="right_panel">
          <TrainsPanel trainManager={trainManager} focusOnTrainClick={handleTrainChange} current_train_index={current_train_index}/>
        </div>
        <div className="bottom_panel">
          <BottomPanel currentTrain={trainManager.trains[current_train_index]} handleWagonChange={handleWagonChange} current_wagon_index={current_wagon_index} handleSeatChange={handleSeatChange} current_seat_index={current_seat_index}/>
        </div>
    </div>
  );
}

export default TrainSimulation;
