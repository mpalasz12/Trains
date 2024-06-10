package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Traveler {
	private String firstName;
	private String lastName;
	private String mailAddress;


	public Traveler() {}
	public Traveler(String firstName, String lastName, String mail) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mailAddress = mail;
	}

	public String getFirstName() {
	return firstName;
	}

	public void setFirstName(String firstName) {
	this.firstName = firstName;
	}

	public String getLastName() {
	return lastName;
	}

	public void setLastName(String lastName) {
	this.lastName = lastName;
	}

	public String getMailAddress() {
	return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
	this.mailAddress = mailAddress;
	}
}
