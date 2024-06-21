import React from "react";
import './Sidebar.css';

function Sidebar({ onButtonClick }) {
    return (
        <div className="sidebar">
            <button className="sidebar-button" onClick={() => onButtonClick('Mapa')}>Mapa</button>
            <button className="sidebar-button" onClick={() => onButtonClick('Kup bilet')}>Kup bilet</button>
            <button className="sidebar-button" onClick={() => onButtonClick('Sprawdź bilet')}>Sprawdź bilet</button>
            <button className="sidebar-dev_button" onClick={() => onButtonClick('Statystyki')}>Statystyki</button>
        </div>
    );
}

export default Sidebar;