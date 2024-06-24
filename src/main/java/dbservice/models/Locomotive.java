package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Locomotive {
	Integer locomotive_id;
	String model;
	String origin_country;

	public Locomotive() {
	}

	public Locomotive(String model, String origin_country) {
		this.model = model;
		this.origin_country = origin_country;
	}

	public Integer getLocomotive_id() {
		return locomotive_id;
	}

	public void setLocomotive_id(Integer locomotive_id) {
		this.locomotive_id = locomotive_id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOrigin_country() {
		return origin_country;
	}

	public void setOrigin_country(String origin_country) {
		this.origin_country = origin_country;
	}
}
