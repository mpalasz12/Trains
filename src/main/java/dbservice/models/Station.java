package dbservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Station {
	private int id;
	private String name;
	private int cityID;

	public Station(){}
	public Station(String name, int cityID) {
		this.name = name;
		this.cityID = cityID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCityID() {
		return cityID;
	}

	public void setName(int id) {
		this.cityID = id;
	}

	@Column(name = "station_id")
	public int getId() {return id;}

	public void setId(int id) {this.id = id;}

}
