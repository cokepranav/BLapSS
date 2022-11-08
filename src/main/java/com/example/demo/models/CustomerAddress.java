package com.example.demo.models;

public class CustomerAddress {

	private int customerId;

	private String street;

	private String city;

	private String country;

	private String postalcode;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String pincode) {
		this.postalcode = postalcode;
	}

	@Override
	public String toString() {
		return   street +", "+ city+ ", " +
				postalcode + ", "+ country;
	}


}