import React, { useState } from "react";
import Sidebar from "./Sidebar";
import TicketForm from "./TicketForm";
import './App.css';
import Statistics from "./Statistics";
import TicketCheck from "./TicketCheck";
import AddLine from "./AddLine";
import StartTrain from "./StartTrain";
import FindConnection from "./FindConnection";
import TrainSimulation from "./TrainSimulation";
import axios from 'axios';

function App() {
    const [activeButton, setActiveButton] = useState(null);

    const handleButtonClick = (buttonName) => {
        setActiveButton(buttonName);
    };

    return (
        <div className="app">
            <Sidebar onButtonClick={handleButtonClick} />
            <div className="app-content">
                {activeButton === 'Mapa' && <TrainSimulation />}
                {activeButton === 'Kup bilet' && <TicketForm />}
                {activeButton === 'Sprawdź bilet' && <TicketCheck />}
                {activeButton === 'Sprawdź połączenie' && <FindConnection />}
                {activeButton === 'Wystartuj pociąg' && <StartTrain />}
                {activeButton === 'Dodaj linię' && <AddLine />}
                {activeButton === 'Statystyki' && <Statistics />}
            </div>
        </div>
    );
}

export default App;