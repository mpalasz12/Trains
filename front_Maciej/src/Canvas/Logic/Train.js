
class Train {
    constructor(name, locomotive, wagons, track)
    {
      this.posX = track.getCurrentStation().posX;
      this.posY = track.getCurrentStation().posY;
      this.track = track;
      this.name = name;
      this.locomotive = locomotive;
      this.wagons = wagons;
      this.distanceToDestination = this.calculateDistanceToDestination();
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
      const distanceToTravel = this.locomotive.speed * deltaTime/ 50000;
      const distanceRatio = distanceToTravel / this.distanceToDestination;
  
      this.posX += (this.track.getNextStation().posX - this.posX) * distanceRatio;
      this.posY += (this.track.getNextStation().posY - this.posY) * distanceRatio;
  

      if(this.distanceToDestination < 1)
        this.track.goToNextTation();
      
      this.distanceToDestination = this.calculateDistanceToDestination();
    }

    calculateDistanceToDestination() {
      return Math.sqrt(
        Math.pow(this.track.getNextStation().posX - this.posX, 2) +
        Math.pow(this.track.getNextStation().posY - this.posY, 2)
      );
    }
}

export default Train;