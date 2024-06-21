package dbservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class City {
	private long city_id;
	private String name;

	public City(){}
	public City(String name) {
		this.name = name;
	}
	public City(int city_id, String name) {
		this.city_id = city_id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {return city_id;}

	public void setId(long id) {this.city_id = id;}
}
