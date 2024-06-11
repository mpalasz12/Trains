import React from "react";
import ReactDOM from "react-dom/client";

// Definiujemy prosty komponent App
function App() {
    return (
        <div>
            <h1>Hello, world!</h1>
        </div>
    );
}

// Renderujemy komponent App
ReactDOM.createRoot(document.getElementById("root")).render(
    <App />
);