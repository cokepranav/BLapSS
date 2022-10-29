package com.example.demo.models;

public class Shipper {
	private int shipperId;

	private int name;

	private String phoneNumber;

	private int rating;

	public int getShipperId() {
		return shipperId;
	}

	public void setShipperId(int shipperId) {
		this.shipperId = shipperId;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

//	public String get

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Shipper [shipperId=" + shipperId + ", name=" + name + ", phoneNumber=" + phoneNumber + ", rating="
				+ rating + "]";
	}

}