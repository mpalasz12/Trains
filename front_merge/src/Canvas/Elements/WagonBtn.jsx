import React from "react";

function WagonBtn({isFocused, handleWagonClick, wagon_number}) {
    return (
        <>
        {isFocused ? (
            <button className="btn_wagon_focused" key={wagon_number} onClick={() => handleWagonClick(wagon_number)}>
                {wagon_number + 1}
            </button>
        ) : (
            <button className="btn_wagon" key={wagon_number} onClick={() => handleWagonClick(wagon_number)}>
                {wagon_number + 1}
            </button>
        )}
        </>
    );
}

export default WagonBtn;