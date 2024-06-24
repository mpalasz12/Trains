import React, { useState } from 'react';
import './FindConnection.css';
import axios from 'axios';


function FindConnection() {
    const [startStation, setStartStation] = useState('');
    const [endStation, setEndStation] = useState('');
    const [lineID, setLineID] = useState('');

    const handleStartStationChange = (event) => {
        setStartStation(event.target.value);
    };

    const handleEndStationChange = (event) => {
        setEndStation(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        console.log(`Stacja początkowa: ${startStation}, Stacja końcowa: ${endStation}`);
        try {
            console.log(ticketData);
            const response = await axios.get('http://localhost:8080/data/DO_UZUPEŁNIENIA', null, {
                params: {
                    start_name: startStation,
                    end_name: endStation,
                }
            });
            console.log("Connection searched successfully");
            setLineID(response.data.line_id);
        } catch (error) {
            console.error("Error searching connention:", error);
            return;
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
        </form>
    );
}

export default FindConnection;