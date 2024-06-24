import axios from 'axios';

class Train {
    constructor(name, locomotive, wagons, track, train_id)
    {
      this.posX = track.getCurrentStation().posX;
      this.posY = track.getCurrentStation().posY;
      this.track = track;
      this.name = name;
      this.locomotive = locomotive;
      this.wagons = wagons;
      this.distanceToDestination = this.calculateDistanceToDestination();
      this.train_id = train_id;
    }

    get_occupied_seats()
    {
      let occupied_seats = 0;
      this.wagons.forEach(wagon => {
        wagon.seats.forEach(seat => {
          occupied_seats += seat.isOccupied ? 1 : 0;
        });
      });
      return occupied_seats;
    }

    get_all_seats()
    {
      let seats = 0;
      this.wagons.forEach(wagon => {
        seats += wagon.seats.length;
      });
      return seats;
    }

    update_position(deltaTime)
    {
      const distanceToTravel = this.locomotive.speed * deltaTime/ 5000;
      const distanceRatio = distanceToTravel / this.distanceToDestination;
  
      this.posX += (this.track.getNextStation().posX - this.posX) * distanceRatio;
      this.posY += (this.track.getNextStation().posY - this.posY) * distanceRatio;
  

      if(this.distanceToDestination < 1)
      {
        this.track.goToNextTation();
        this.updateTickets();
      }
      
      this.distanceToDestination = this.calculateDistanceToDestination();
    }

    calculateDistanceToDestination() {
      return Math.sqrt(
        Math.pow(this.track.getNextStation().posX - this.posX, 2) +
        Math.pow(this.track.getNextStation().posY - this.posY, 2)
      );
    }

    resetWagonsSeats()
    {
      for(let i = 0; i < this.wagons.length; i++)
      {
        this.wagons[i].resetSeats();
      }
    }

    async updateTickets(){
      await axios.post('http://localhost:8080/data/advance_train', null, {
        params: {
            train_id: this.train_id
        }
      });

      const tickets = await axios.get("http://localhost:8080/data/maciek_ticket", {
        params: { 
          train_id: this.train_id
        }
      });
      this.resetWagonsSeats();
      for(let i = 0; i < tickets.data.length; i++)
      {
        let ticket = tickets.data[i];
        this.wagons[ticket.wagon_num-1].occupySeat(ticket.seat_num-1, ticket.traveler_name, ticket.traveler_surname, ticket.end_station);
      }
    }
}

export default Train;