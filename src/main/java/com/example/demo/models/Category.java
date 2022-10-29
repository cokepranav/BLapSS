package com.example.demo.models;

public class Category {

	private int categoryId;

	private String title;

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

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", title=" + title + "]";
	}

}