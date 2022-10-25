package com.example.demo.models;

public class Review {
	
	private int customerId;
	
	private int productId;
	
	private String description;
	
	private int rating;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Review [customerId=" + customerId + ", productId=" + productId + ", description=" + description
				+ ", rating=" + rating + "]";
	}
	
	
}
