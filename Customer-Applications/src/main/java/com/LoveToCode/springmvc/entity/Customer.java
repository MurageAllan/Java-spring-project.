package com.LoveToCode.springmvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.LoveToCode.springmvc.entity.validation.CustomerCode;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;

	@Column(name = "first_name")
	@NotNull(message = "cannot be empty")
	@Size(min = 2, message = "must be two or more chars")
	private String firstName;

	@Column(name = "last_name")
	@NotNull(message = "cannot be empty")
	@Size(min = 2, message = "must be two or more chars")
	private String lastName;

	@Column(name = "email")
	@NotBlank(message = "cannot be empty")
	private String emailAddress;

	@Column(name = "age")
	@Min(value = 20)
	@Max(value = 70)
	private Integer age;

	@Column(name = "country")
	private String country;

	@Column(name = "hobbies")
	private String hobbies;

	@Column(name = "operating_system")
	private String operatingSystem;

	@Column(name = "postal_code")
	@CustomerCode
	private String postalCode;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
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

	public Customer(Integer customerId,
			@NotNull(message = "cannot be empty") @Size(min = 2, message = "must be two or more chars") String firstName,
			@NotNull(message = "cannot be empty") @Size(min = 2, message = "must be two or more chars") String lastName,
			@NotNull(message = "cannot be empty") String emailAddress, @Min(20) @Max(70) Integer age, String country,
			String hobbies, String operatingSystem, String postalCode) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.age = age;
		this.country = country;
		this.hobbies = hobbies;
		this.operatingSystem = operatingSystem;
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailAddress=" + emailAddress + ", age=" + age + ", country=" + country + ", hobbies=" + hobbies
				+ ", operatingSystem=" + operatingSystem + ", postalCode=" + postalCode + "]";
	}

	public Customer() {

	}
}
