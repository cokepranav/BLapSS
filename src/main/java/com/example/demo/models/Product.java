package com.example.demo.models;

public class Product {

	private int productId;

	private int categoryId;

	private String title;

	private String imageLink;

	private String description;

	private float unitPrice;

	private int smallInStock;

	private int mediumInStock;

	private int largeInStock;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getSmallInStock() {
		return smallInStock;
	}

	public void setSmallInStock(int smallInStock) {
		this.smallInStock = smallInStock;
	}

	public int getMediumInStock() {
		return mediumInStock;
	}

	public void setMediumInStock(int mediumInStock) {
		this.mediumInStock = mediumInStock;
	}

	public int getLargeInStock() {
		return largeInStock;
	}

	public void setLargeInStock(int largeInStock) {
		this.largeInStock = largeInStock;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", categoryId=" + categoryId + ", title=" + title + ", imageLink="
				+ imageLink + ", description=" + description + ", unitPrice=" + unitPrice + ", smallInStock="
				+ smallInStock + ", mediumInStock=" + mediumInStock + ", largeInStock=" + largeInStock + "]";
	}


}