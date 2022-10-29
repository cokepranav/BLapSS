package com.example.demo.models;

public class Cart {

	private int cartId;

	private String createdDate;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", createdDate=" + createdDate + "]";
	}

}