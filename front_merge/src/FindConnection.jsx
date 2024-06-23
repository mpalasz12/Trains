import React, { useState } from 'react';
import './FindConnection.css';

function FindConnection() {
    const [startStation, setStartStation] = useState('');
    const [endStation, setEndStation] = useState('');

    const handleStartStationChange = (event) => {
        setStartStation(event.target.value);
    };

    const handleEndStationChange = (event) => {
        setEndStation(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log(`Stacja początkowa: ${startStation}, Stacja końcowa: ${endStation}`);
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