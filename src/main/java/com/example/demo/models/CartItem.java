package com.example.demo.models;

import com.example.demo.dao.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CartItem {

	@Autowired
	ProductRepository productRepository;

	private int customerId;

	private int productId;

	private float unitPrice;

	private int quantity;

	private String size;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int cartId) {
		this.customerId = customerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "CartItem [customerId=" + customerId + ", productId=" + productId + ", unitPrice=" + unitPrice + ", quantity="
				+ quantity + ", size=" + size + "]";
	}

	public String getProductTitle() {
		Product product = productRepository.getProductById(this.productId);
		return product.getTitle();
	}

}