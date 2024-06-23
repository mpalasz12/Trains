class Track
{
    constructor(stations, onStationChange)
    {
        this.stations = stations
        this.current_station_index = 0;
        this.next_station_index = 1;
        this.going_forward = true;
        this.onStationChange = onStationChange;
    }

    goToNextTation()
    {
        this.current_station_index = this.next_station_index;

        if(this.current_station_index === this.stations.length-1 || this.current_station_index === 0)
            this.going_forward = !this.going_forward;
       
        this.going_forward ? this.next_station_index++ : this.next_station_index--;
        this.onStationChange();
    }

    getStartStation()
    {
        return this.going_forward ? this.stations[0] : this.stations[this.stations.length-1];
    }

    getEndStation()
    {
        return this.going_forward ? this.stations[this.stations.length-1] : this.stations[0];
    }

    getCurrentStation()
    {
        return this.stations[this.current_station_index];
    }

    getNextStation()
    {
        return this.stations[this.next_station_index];
    }
}

export default Track;