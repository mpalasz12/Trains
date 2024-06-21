package dbservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Station {
	private Long station_id;
	private String name;
	private Long city_id;

	public Station() {}
	public Station(String name, Long city_id) {
		this.name = name;
		this.city_id = city_id;
	}

	public Long getStation_id() {
		return station_id;
	}

	public void setStation_id(Long station_id) {
		this.station_id = station_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCity_id() {
		return city_id;
	}

	public void setCity_id(Long city_id) {
		this.city_id = city_id;
	}
}
