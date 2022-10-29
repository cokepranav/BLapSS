package com.example.demo.models;

public class CustomerEmail {

	private int customerId;

	private String emailAddress;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return "CustomerEmail [customerId=" + customerId + ", emailAddress=" + emailAddress + "]";
	}

}