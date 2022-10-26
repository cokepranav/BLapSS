package com.example.demo.models;

public class Product {
	
	private int productId;
	
	private int categoryId;
	
	private String title;
	
	private String description;
	
	private float unitPrice;
	
	private int inventory;

	public Product(int p_id,int c_id,String title,String description,float price,int inv){
		productId=p_id;
		categoryId=c_id;
		this.title=title;
		this.description=description;
		unitPrice=price;
		inventory=inv;
	}

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

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", categoryId=" + categoryId + ", title=" + title + ", description="
				+ description + ", unitPrice=" + unitPrice + ", inventory=" + inventory + "]";
	}
	
}
