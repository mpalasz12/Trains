import React, { useState } from 'react';
import './AddLine.css';
import axios from 'axios';

function AddLine() {
    const [lineData, setLineData] = useState({
        stations: [''],
    });

    const handleChange = (event, index) => {
        const newStations = [...lineData.stations];
        newStations[index] = event.target.value;
        setLineData({
            ...lineData,
            stations: newStations
        });
    };

    const handleAddStation = () => {
        setLineData({
            ...lineData,
            stations: [...lineData.stations, '']
        });
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        console.log(lineData);
        try {
            const response = await axios.post("http://localhost:8080/do_uzupelnienia", lineData);
            if (response.status === 200) {
                alert('Linia została dodana pomyślnie!');
            } else {
                alert('Wystąpił błąd podczas dodawania linii.');
            }
        } catch (error) {
            console.error('There was an error!', error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            {lineData.stations.map((station, index) => (
                <label key={index}>
                    Stacja {index + 1}:
                    <input
                        type="text"
                        name="station"
                        value={station}
                        onChange={(e) => handleChange(e, index)}
                    />
                </label>
            ))}
            <button type="button" onClick={handleAddStation}>Dodaj stację</button>
            <input type="submit" value="Dodaj linię" />
        </form>
    );
}

export default AddLine;