package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class City {
	private int id;
	private String name;

	public City(){}
	public City(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {return id;}

	public void setId(int id) {this.id = id;}
}
