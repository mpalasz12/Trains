import Locomotive from "./Locomotive";
import Train from "./Train"
import Wagon from "./Wagon";

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

        var wkd_wagons = [
            new Wagon("wkd1", 1, 20),
            new Wagon("wkd2", 1, 20),
            new Wagon("wkd3", 1, 28),
            new Wagon("wkd4", 2, 28),
            new Wagon("wkd5", 2, 28),
            new Wagon("wkd6", 3, 28)];
            
        var skm_wagons = [
            new Wagon("skm1", 1, 28),
            new Wagon("skm2", 2, 28)];
            
        var ls_wagons = [
            new Wagon("ls1", 1, 28),
            new Wagon("ls2", 2, 28),
            new Wagon("ls3", 2, 28),
            new Wagon("ls4", 2, 28)];
            
        var ic_wagons = [
            new Wagon("ic1", 1, 10),
            new Wagon("ic2", 1, 10),
            new Wagon("ic3", 1, 10),
            new Wagon("ic4", 1, 10),
            new Wagon("ic5", 1, 10),
            new Wagon("ic6", 1, 10),
            new Wagon("ic7", 1, 10)];

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