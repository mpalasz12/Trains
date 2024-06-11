class Seat{
    constructor(number)
    {
        this.number = number;
        this.isOccupied = false;
    }

    occupy(traveler)
    {
        this.traveler = traveler;
        this.isOccupied = true;
    }

    free()
    {
        this.traveler = null;
        this.isOccupied = false;
    }
}

export default Seat;