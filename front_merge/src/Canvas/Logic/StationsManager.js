import Station from "./Station";

export function addCity(city) {
	try {
		const params = new URLSearchParams();
		params.append("name", city);
		const response = axios.post('http://localhost:8080/data/add_city?' + params.toString());
		console.log('Answer from server: ', response.data);
	} catch (error) {
		console.error("Error adding locomotive: ", error);
	}
}

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
  
  initCities() {
    this.stations.forEach(station => {
      addCity(station.name);
    });
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