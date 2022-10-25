package com.example.demo.models;

public class CartItem {
	
	private int cartId;
	
	private int productId;
	
	private int quantity;
	
	private float unitPrice;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "CartItem [cartId=" + cartId + ", productId=" + productId + ", quantity=" + quantity + ", unitPrice="
				+ unitPrice + "]";
	}
	
	
}
