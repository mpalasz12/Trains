import React from "react";
import './Sidebar.css';

function Sidebar({ onButtonClick }) {
    return (
        <div className="sidebar">
            <div className="sidebar-buttons">
                <button className="sidebar-button" onClick={() => onButtonClick('Mapa')}>Mapa</button>
                <button className="sidebar-button" onClick={() => onButtonClick('Kup bilet')}>Kup bilet</button>
                <button className="sidebar-button" onClick={() => onButtonClick('Sprawdź bilet')}>Sprawdź bilet</button>
            </div>
            <div className="sidebar-dev_buttons">
                <button className="sidebar-dev_button" onClick={() => onButtonClick('Wystartuj pociąg')}>Wystartuj pociąg</button>
                <button className="sidebar-dev_button" onClick={() => onButtonClick('Dodaj linię')}>Dodaj linię</button>
                <button className="sidebar-dev_button" onClick={() => onButtonClick('Statystyki')}>Statystyki</button>
            </div>
        </div>
    );
}

export default Sidebar;