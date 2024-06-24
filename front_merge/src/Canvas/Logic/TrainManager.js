import Locomotive from "./Locomotive";
import Train from "./Train";
import axios from "axios";
import Wagon from "./Wagon";

// NA RAZIE ZAKOMENTOWANE BO LUKASZ ROBI INACZEJ
//
// Dodałem póki co metode do lokomotyw i wagonów
// Kolejność powinna być taka:
// 1. Dodanie lokomotywy
// 2. Dodanie pociągu i zebranie jego ID
// 3. Dodanie wagonów z podpiętym train_id, numery wagonów dodać w pętli (obsługuje to processWagonList)

// function for adding locomotive to database api
//export function addLocomotive(locomotive) {
//	event.preventDefault();
//	try {
//		const params = new URLSearchParams();
//		params.append("model", locomotive.name);
//		params.append("origin_country", locomotive.country);
//		const response = axios.post('http://localhost:8080/data/add_locomotive?' + params.toString());
//		console.log('Answer from server: ', response.data);
//	} catch (error) {
//		console.error("Error adding locomotive: ", error);
//	}
//}

//export function addWagon(wagon, train_id, wagon_num) {
//	event.preventDefault();
//	try {
//		const params = new URLSearchParams();
//		params.append("wagon_num", wagon_num);
//		params.append("wagon_capacity", wagon.capacity);
//		params.append("train_id", train_id);
//		const response = axios.post('http://localhost:8080/data/add_wagon?' + params.toString());
//		console.log('Answer from server: ', response.data);
//	} catch (error) {
//		console.error("Error adding wagon: ", error);
//	}
//}
//
//export function processWagonList(wagonList, trainID) {
//	// iterate over wagons and add to db
//	for (let i = 0; i < wagonList.length; i++) {
//		addWagon(wagonList[i], trainID, String(i));
//	}
//}

class TrainManager
{
    constructor(tracksManager)
    {
        this.tracksManager = tracksManager;
        this.trains = this.init_trains();
    }

    getCurrentTrain()
    {
        return this.trains[this.selectedTrainIndex];
    }

    init_trains()
    {
        // Locomotives
        var IC9215 = new Locomotive("IC9215", "Poland", 200);
        var LI3122 = new Locomotive("LI3122", "Germany", 180);
        var KK1233 = new Locomotive("KK1233", "Sweden", 50);
		//addLocomotive(IC9215);
		//addLocomotive(LI3122);
		//addLocomotive(KK1233);

        var wkd_wagons = [
            new Wagon("wkd1", 1, 20),
            new Wagon("wkd2", 1, 20),
            new Wagon("wkd3", 3, 20)];

		// iterate over wagons and add to db
		//processWagonList(wkd_wagons, 1);
		
            
        var skm_wagons = [
            new Wagon("skm1", 1, 28),
            new Wagon("skm2", 2, 28)];

		// same
		//for (let i = 0; i < skm_wagons.length; i++) {
		//	addWagon(skm_wagons[i], 2, String(i));
		//}
            
        var ls_wagons = [
            new Wagon("ls1", 1, 28),
            new Wagon("ls2", 2, 28),
            new Wagon("ls3", 2, 28),
            new Wagon("ls4", 2, 28)];

		// same
		//for (let i = 0; i < ls_wagons.length; i++) {
		//	addWagon(ls_wagons[i], 3, String(i));
		//}
            
        var ic_wagons = [
            new Wagon("ic1", 1, 10),
            new Wagon("ic2", 1, 10),
            new Wagon("ic3", 1, 10),
            new Wagon("ic4", 1, 10),
            new Wagon("ic5", 1, 10),
            new Wagon("ic6", 1, 10),
            new Wagon("ic7", 1, 10)];

		// same
		//for (let i = 0; i < ic_wagons.length; i++) {
		//	addWagon(ic_wagons[i], 4, String(i));
		//}

        // Trains
        var WKD = new Train("WKD", IC9215, wkd_wagons, this.tracksManager.tracks[0]);
        var SKM = new Train("SKM", LI3122, skm_wagons, this.tracksManager.tracks[1]);
        var LS = new Train("ŁS", LI3122, ls_wagons, this.tracksManager.tracks[2]);
        var IC = new Train("IC", KK1233, ic_wagons, this.tracksManager.tracks[3]);

        return [
            WKD,
            SKM,
            LS,
            IC];
    }
    
    update_trains_positions(deltatime)
    {
        this.trains.forEach(train => train.update_position(deltatime))
    }

}

export default TrainManager;
