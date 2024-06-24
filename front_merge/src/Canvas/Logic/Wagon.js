import Seat from "./Seat"
import Traveler from "./Traveler";

class Wagon {
    constructor(model, class_name, capacity)
    {
        this.model = model;
        this.capacity = capacity
        this.class = class_name;
        this.seats = Array(capacity).fill(null);
        this.resetSeats();
        // this.occupyRandomSeats(capacity/4);
    }

    resetSeats()
    {
      for(let i = 0; i < this.capacity; i++)
      {
          this.seats[i] = new Seat(i+1, "", false)
      }
    }

    occupySeat(seat_num, traveler_name, traveler_surname, end_station, ticket_id)
    {
        var traveler = new Traveler(traveler_name, traveler_surname, end_station, ticket_id);
        this.seats[seat_num].occupy(traveler);
    }

    // occupyRandomSeats(numSeatsToOccupy) {
    //   var FooTraveler = new Traveler("Marek", "Mostowiak", "Babice", "69420");
    //   const seatIndices = Array.from({ length: this.capacity }, (_, index) => index);
    //   seatIndices.sort(() => Math.random() - 0.5);
    //   for (let i = 0; i < numSeatsToOccupy; i++) {
    //     const seatIndex = seatIndices[i];
    //     if (seatIndex !== undefined) {
    //       this.seats[seatIndex].occupy(FooTraveler);
    //     }
    //   }
    // }

}

export default Wagon;