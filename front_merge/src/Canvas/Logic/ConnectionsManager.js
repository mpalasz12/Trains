import Connection from "./Connection";

class ConnectionsManager
{
    constructor(StationsManager)
    {
        this.connections = [
            new Connection(StationsManager.findStationByName("Kołobrzeg"), StationsManager.findStationByName("Gdańsk")),
            new Connection(StationsManager.findStationByName("Kraków"), StationsManager.findStationByName("Rzeszów")),
            new Connection(StationsManager.findStationByName("Warszawa"), StationsManager.findStationByName("Łódź")),
            new Connection(StationsManager.findStationByName("Łódź"), StationsManager.findStationByName("Poznań")),
            new Connection(StationsManager.findStationByName("Warszawa"), StationsManager.findStationByName("Białystok")),
            new Connection(StationsManager.findStationByName("Wrocław"), StationsManager.findStationByName("Poznań")),
            new Connection(StationsManager.findStationByName("Warszawa"), StationsManager.findStationByName("Gdańsk")),
            new Connection(StationsManager.findStationByName("Warszawa"), StationsManager.findStationByName("Kraków")),
        ];
    }
}

export default ConnectionsManager