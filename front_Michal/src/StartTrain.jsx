import React, { useState } from 'react';
import './StartTrain.css';
import axios from 'axios';

function StartTrain() {
    const [trainId, setTrainId] = useState('');

    const handleChange = (event) => {
        setTrainId(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await axios.post("http://localhost:8080/do_uzupelnienia", { id: trainId });
            if (response.status === 200) {
                alert('Pociąg został uruchomiony pomyślnie!');
            } else {
                alert('Wystąpił błąd podczas uruchamiania pociągu.');
            }
        } catch (error) {
            console.error('There was an error!', error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                ID Pociągu:
                <input
                    type="text"
                    name="trainId"
                    value={trainId}
                    onChange={handleChange}
                />
            </label>
            <input type="submit" value="Uruchom pociąg" />
        </form>
    );
}

export default StartTrain;