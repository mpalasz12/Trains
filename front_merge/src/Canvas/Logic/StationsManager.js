import Station from "./Station";
import axios from 'axios';

// export function addCity(city) {
// 	try {
// 		const params = new URLSearchParams();
// 		params.append("name", city);
// 		const response = axios.post('http://localhost:8080/data/add_city?' + params.toString());
// 		console.log('Answer from server: ', response.data);
// 	} catch (error) {
// 		console.error("Error adding locomotive: ", error);
// 	}
// }

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
      this.StationDict = {
        "Gdansk": [230, 65],
        "Kołobrzeg": [90, 75],
        "Białystok": [432, 165],
        "Warszawa": [340, 230],
        "Poznań": [145, 210],
        "Łódź": [270, 270],
        "Wrocław": [150, 320],
        "Krakow": [300, 402],
        "Rzeszów": [400, 395],
      }
  }
  
  async init_stations() {
    console.log(`Adding stasdasdasdadsadasdadsation:`);
    try {
      console.log(`Adding stasdasdasdadsadasdadsation:`);
        const response = await axios.get("http://localhost:8080/data/get_all_cities");

        console.log(`Adding stasdasdasdadsadauhaisdufyaisdofosaidfoasdsdadsation:`);
        console.log(response.data);

        const stations = response.data;
        stations.forEach(station => {
          const coords = this.StationDict[station.name];
          if (coords) {
            console.log(`Adding station: ${station.name}, X: ${coords[0]}, Y: ${coords[1]}`);
            this.stations.push(new Station(station.name, coords[0], coords[1]));
          } else {
            console.warn(`Coordinates for station ${station.name} not found in StationDict.`);
          }
        });
        return stations;
    } catch (error) {
        console.error("Error initializing stations:", error);
        return [];
    }
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