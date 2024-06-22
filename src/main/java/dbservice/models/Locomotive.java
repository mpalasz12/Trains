package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Locomotive {
	Long id;
	String model;
	String originCountry;

	public Locomotive() {
	}

	public Locomotive(String model, String originCountry) {
		this.model = model;
		this.originCountry = originCountry;
	}

	// getters and setters
	public Long getID() {
		return id;
	}

	public void setID(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
}
