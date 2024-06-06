package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Passenger {
	private String firstName;
	private String lastName;
	private String mailAddress;


	public Passenger() {}
	public Passenger(String firstName, String lastName, String mail) {
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
