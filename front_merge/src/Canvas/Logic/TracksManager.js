import Track from "./Track";

class TracksManager
{
    constructor(stationsManager, onStationChange)
    {
        var track1 = new Track([
            stationsManager.findStationByName("Kołobrzeg"),
            stationsManager.findStationByName("Gdańsk"),
            stationsManager.findStationByName("Warszawa"),
            stationsManager.findStationByName("Kraków"),
            stationsManager.findStationByName("Rzeszów")
        ], onStationChange);

        var track2 = new Track([
            stationsManager.findStationByName("Wrocław"),
            stationsManager.findStationByName("Poznań"),
            stationsManager.findStationByName("Łódź"),
            stationsManager.findStationByName("Warszawa"),
            stationsManager.findStationByName("Białystok")
        ], onStationChange);

        var track3 = new Track([
            stationsManager.findStationByName("Łódź"),
            stationsManager.findStationByName("Warszawa"),
            stationsManager.findStationByName("Białystok")
        ], onStationChange);

        var track4 = new Track([
            stationsManager.findStationByName("Kraków"),
            stationsManager.findStationByName("Rzeszów")
        ], onStationChange);

        this.tracks = [
            track1,
            track2,
            track3,
            track4
        ]
    }

    async init_lines() {
        try {
            const response = await axios.get("http://localhost:8080/data/get_all_lines");    
            const stations = response.data;
            stations.forEach(station => {
              
            });
            return stations;
        } catch (error) {
            console.error("Error initializing stations:", error);
            return [];
        }
      }
    

}
export default TracksManager;
