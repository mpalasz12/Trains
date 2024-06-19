package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Locomotive {
	String model;
	String originCountry;

	public Locomotive() {
	}

	// getters and setters
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
