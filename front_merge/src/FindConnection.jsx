import React, { useState } from 'react';
import './FindConnection.css';
import axios from 'axios';

function FindConnection() {
    const [startStation, setStartStation] = useState('');
    const [endStation, setEndStation] = useState('');
    const [connectionData, setConnectionData] = useState([]);
    const [locomotiveData, setLocomotiveData] = useState('');
    const [error, setError] = useState('');

    const handleStartStationChange = (event) => {
        setStartStation(event.target.value);
    };

    const handleEndStationChange = (event) => {
        setEndStation(event.target.value);
    };

    const fetchActiveTrains = async () => {
        try {
            const response = await axios.get('http://localhost:8080/data/get_all_trains', null);
            return response.data;
        } catch (error) {
            console.error("Error fetching train details:", error);
            setError('Error fetching train details. Please try again.');
        }
    }

    const fetchLocomotiveDetails = async (locomotiveId) => {
        try {
            console.log(locomotiveId);
            const response = await axios.get('http://localhost:8080/data/get_locomotive_by_id', {
                params: { locomotive_id: locomotiveId }
            });
            return response.data;
        } catch (error) {
            console.error("Error fetching locomotive details:", error);
            setError('Error fetching locomotive details. Please try again.');
        }
    };

    const fetchStationName = async (linestopId) => {
        try {
            const response = await axios.get("http://localhost:8080/data/get_station_by_linestop_id", {
                params: { linestop_id: linestopId }
            });
            return response.data.name;
        } catch (error) {
            console.error(`Error fetching station name for linestop ID ${linestopId}`, error);
            return null;
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        console.log(`Stacja początkowa: ${startStation}, Stacja końcowa: ${endStation}`);
        setError('');
        setConnectionData([]);
        setLocomotiveData('');

        try {
            const response = await axios.get('http://localhost:8080/data/find_connection', {
                params: {
                    start_name: startStation,
                    end_name: endStation,
                }
            });
            console.log("Connection searched successfully");
            if (response.data) {
                setConnectionData(response.data);

                var ConnectionData = response.data;
                var TrainData = await fetchActiveTrains();

                for (let i = 0; i < TrainData.length; i++) {
                    if (TrainData[i].line_id == ConnectionData[0]) {
                        var locomotive_id = TrainData[i].locomotive_id;
                    }
                }

                setLocomotiveData(await fetchLocomotiveDetails(locomotive_id));

                const firstStopName = await fetchStationName(ConnectionData[1]);
                const lastStopName = await fetchStationName(ConnectionData[2]);

                connectionData.push(ConnectionData[0]);
                connectionData.push(firstStopName);
                connectionData.push(lastStopName);

                setConnectionData(connectionData);

            } else {
                setError('No connection found between the given stations.');
            }
            
        } catch (error) {
            console.error("Error searching connection:", error);
            setError('Error searching for connection. Please try again.');
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Stacja początkowa:
                <input
                    type="text"
                    name="startStation"
                    value={startStation}
                    onChange={handleStartStationChange}
                />
            </label>
            <label>
                Stacja końcowa:
                <input
                    type="text"
                    name="endStation"
                    value={endStation}
                    onChange={handleEndStationChange}
                />
            </label>
            <input type="submit" value="Znajdź połączenie" />
            {error && <div className="error-message">{error}</div>}
            {connectionData && (
                <div className="connection-details">
                    <h2>Connection Details</h2>
                    <p>Line ID: {connectionData[0]}</p>
                    <p>First Stop: {connectionData[1]}</p>
                    <p>Last Stop: {connectionData[2]}</p>
                    {locomotiveData && (
                        <>
                            <p>Locomotive Model: {locomotiveData.model}</p>
                            <p>Locomotive Origin Country: {locomotiveData.origin_country}</p>
                        </>
                    )}
                </div>
            )}
        </form>
    );
}

export default FindConnection;
