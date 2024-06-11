import Station from "./Station";

class StationsManager
{
    constructor()
    {
        this.stations = [
            new Station("Gdańsk", 230, 65),
            new Station("Kołobrzeg", 90, 75),
            new Station("Białystok", 432, 165),
            new Station("Warszawa", 340, 230),
            new Station("Poznań", 145, 210),
            new Station("Łódź", 270, 270),
            new Station("Wrocław", 150, 320),
            new Station("Kraków", 300, 402),
            new Station("Rzeszów", 400, 395)
        ];
    }

    findStationByName(name) {
        for (const station of this.stations) {
          if (station.name === name) {
            return station;
          }
        }
        return null;
      }
}

export default StationsManager;