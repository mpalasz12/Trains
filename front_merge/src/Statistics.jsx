import React, { useEffect, useState } from "react";
import axios from "axios";
import './Statistics.css';

function Statistics() {
  const [trains, setTrains] = useState([]);

  useEffect(() => {
    const fetchTrainData = async () => {
      try {
        // Fetch all trains
        const response = await axios.get("http://localhost:8080/data/get_active_trains");
        const trainsData = await Promise.all(response.data.map(async (train) => {
          // Fetch wagons for each train
          const wagonsResponse = await axios.get("http://localhost:8080/data/get_train_wagons", {
            params: {
              train_id: train.id
            }
          });

          // Fetch train capacity
          const capacityResponse = await axios.get("http://localhost:8080/data/get_train_capacity", {
            params: {
              train_id: train.id
            }
          });

          // Fetch seats taken
          const seatsTakenResponse = await axios.get("http://localhost:8080/data/get_seats_taken", {
            params: {
              train_id: train.id,
              wagon_num: 1 // Assuming we're checking the first wagon; adjust as needed
            }
          });

          // Fetch station names
          const startStationResponse = await axios.get("http://localhost:8080/data/city", {
            params: {
              id: train.first_stop_id
            }
          });

          const endStationResponse = await axios.get("http://localhost:8080/data/city", {
            params: {
              id: train.last_stop_id
            }
          });

          const currentStationResponse = await axios.get("http://localhost:8080/data/city", {
            params: {
              id: train.curr_linestop
            }
          });

          return {
            id: train.id,
            wagons: wagonsResponse.data.length,
            passengers: seatsTakenResponse.data.length,
            seatOccupancy: (seatsTakenResponse.data.length / capacityResponse.data * 100).toFixed(2),
            startStation: startStationResponse.data,
            endStation: endStationResponse.data,
            currentStation: currentStationResponse.data,
          };
        }));
        setTrains(trainsData);
      } catch (error) {
        console.error("Error fetching train data", error);
      }
    };

    fetchTrainData();
  }, []);

  return (
    <div className="table-container">
      <table className="table">
        <thead>
          <tr>
            <th>Train ID</th>
            <th>Wagons</th>
            <th>Passengers</th>
            <th>Seat Occupancy (%)</th>
            <th>Start Station</th>
            <th>End Station</th>
            <th>Current Station</th>
          </tr>
        </thead>
        <tbody>
          {trains.map((train, index) => (
            <tr key={index}>
              <td>{train.id}</td>
              <td>{train.wagons}</td>
              <td>{train.passengers}</td>
              <td>{train.seatOccupancy}</td>
              <td>{train.startStation}</td>
              <td>{train.endStation}</td>
              <td>{train.currentStation}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Statistics;
