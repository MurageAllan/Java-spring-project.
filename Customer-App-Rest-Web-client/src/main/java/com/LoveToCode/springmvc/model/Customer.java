package com.LoveToCode.springmvc.model;

public class Customer {

	private int id;

	private String firstName;

	private String lastName;

	private String emailAddress;

	private String hobby;

	private String country;

	private int age;

	private String operatingSystem;

	private String postalCode;

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Customer(int id, String firstName, String lastName, String emailAddress, String hobby,
			String country, int age, String operatingSystem, String postalCode) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.hobby = hobby;
		this.country = country;
		this.age = age;
		this.operatingSystem = operatingSystem;
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailAddress=" + emailAddress + ", hobby=" + hobby + ", country=" + country + ", age=" + age
				+ ", operatingSystem=" + operatingSystem + ", postalCode=" + postalCode + "]";
	}

	public Customer() {

	}
}
