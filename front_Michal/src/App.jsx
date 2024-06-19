import React, { useState } from "react";
import Sidebar from "./Sidebar";
import TicketForm from "./TicketForm";
import './App.css';
import Statistics from "./Statistics";

function App() {
    const [activeButton, setActiveButton] = useState(null);

    const handleButtonClick = (buttonName) => {
        setActiveButton(buttonName);
    };

    return (
        <div className="app">
            <Sidebar onButtonClick={handleButtonClick} />
            <div className="app-content">
                {activeButton === 'Kup bilet' && <TicketForm />}
                {activeButton === 'Statystyki' && <Statistics />}
            </div>
        </div>
    );
}

export default App;