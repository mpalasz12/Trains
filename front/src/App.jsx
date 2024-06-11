import React, { useState } from "react";
import Sidebar from "./Sidebar";
import TicketForm from "./TicketForm";
import './App.css';

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
            </div>
        </div>
    );
}

export default App;