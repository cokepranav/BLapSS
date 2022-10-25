package com.example.demo.models;

public class CustomerAddress {
	
	private int customerId;
	
	private String street;
	
	private String city;
	
	private String country;
	
	private String postalCode;
	
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
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	@Override
	public String toString() {
		return "CustomerAddress [customerId=" + customerId + ", street=" + street + ", city=" + city + ", country="
				+ country + ", postalCode=" + postalCode + "]";
	}
	
}
