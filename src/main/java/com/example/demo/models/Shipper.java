package com.example.demo.models;

public class Shipper {
	
	private int shipperId;
	
	private String firstName;
	
	private String lastName;
	
	private String phoneNumber;
	
	private int shipperRating;
	
	public int getShipperId() {
		return shipperId;
	}
	
	public void setShipperId(int shipperId) {
		this.shipperId = shipperId;
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
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public int getShipperRating() {
		return shipperRating;
	}
	
	public void setShipperRating(int shipperRating) {
		this.shipperRating = shipperRating;
	}
	
	@Override
	public String toString() {
		return "Shipper [shipperId=" + shipperId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", shipperRating=" + shipperRating + "]";
	}
	
}
