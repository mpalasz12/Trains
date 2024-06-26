Na stronie zbudowanej w react.js możemy zobaczyć symulację pociągów jeżdżących po Polsce. Po kliknięciu na konkretny pociąg wyświetlają nam się miejsca wolne oraz zajęte w danym pociągu. 
Po kliknięciu na dane miejsce, jeśli jest zajęte ujrzymy dane pasażera, do którego to miejsce należy. Inną opcją strony jest możliwość zakupu biletu, sprawdzenie połączenia oraz sprawdzenie wybranych przez nas statystyk
takich jak: lista stacji, lista aktywnych połączeń czy licza biletóœ zakupionych przez dany e-mail.

Docelowe pliki znajdują się w folderze front_merge, w którym jest src. W folderze src znajdują się pliki tworzące strone a w podfolderze Canvas są pliki tworzące symulację.
Strona jest zbudowana na narzędziu Vite.


Fragment kodu, w którym zostaje wysłane zapytanie do backendu o wszystkie dostępne stacje:
```javascript
try {
    const response = await axios.get("http://localhost:8080/data/all_stations");
    const allStations = response.data;
    const stationNames = allStations.map(station => station.name);
    setFirstStationInDataBase(stationNames.includes(startStation));
    setLastStationInDataBase(stationNames.includes(endStation));

    if (!stationNames.includes(startStation) || !stationNames.includes(endStation)) {
        console.error("We don't have these stations in our database");
        return;
    }
} catch (error) {
    console.error("Error loading all available stations in our database", error);
    return;
}
```

Wysłanie danych kupującego bilet do backendu:
```javascript
try {
    await axios.post('http://localhost:8080/data/add_traveler', null, {
        params: {
            first_name: firstName,
            last_name: lastName,
            mail: mail
        }
    });
    console.log("Traveler added successfully");
    const response = await axios.get("http://localhost:8080/data/get_traveler_id_by_mail", {
        params: { mail: mail }
    });
    travelerID = response.data;
    console.log(travelerID);
} catch (error) {
    console.error("Error adding traveler or fetching traveler ID:", error);
    return;
```

Wysyłanie danych potrzebnych do kupienia biletu, wprowadzonych na stronie, do backendu:
```javascript
try {
    const response = await axios.post('http://localhost:8080/data/add_ticket', null, {
        params: {
            traveler_id: travelerID,
            first_stop: lineData[1],
            last_stop: lineData[2],
            train_id: foundTrainId,
            wagon_num: wagon,
            seat_num: seat
        }
    });
    console.log("Ticket added successfully");
    const ticketResponse = await axios.get('http://localhost:8080/data/tickets_by_mail', {
        params: { mail: mail }
    });
    const tickets = ticketResponse.data;
    setTicketId(tickets[tickets.length - 1].ticket_id);
    setIsSubmitted(true);
} catch (error) {
    console.error("Error adding ticket or fetching ticket ID:", error);
}
```