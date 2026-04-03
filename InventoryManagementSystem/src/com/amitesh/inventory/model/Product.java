package com.amitesh.inventory.model;

public class Product {

	private int id;
	private String name;
	private int thresholdQuantity;
	private ProductType type; // perishable and non-perishable
	private double price;
	private int quantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getThresholdQuantity() {
		return thresholdQuantity;
	}

	public void setThresholdQuantity(int thresholdQuantity) {
		this.thresholdQuantity = thresholdQuantity;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product(int id, String name, int thresholdQuantity, ProductType type, double price, int quantity) {
		this.id = id;
		this.name = name;
		this.thresholdQuantity = thresholdQuantity;
		this.type = type;
		this.price = price;
		this.quantity = quantity;
	}

	public Product(String name, int thresholdQuantity, ProductType type, double price, int quantity) {
		this.name = name;
		this.thresholdQuantity = thresholdQuantity;
		this.type = type;
		this.price = price;
		this.quantity = quantity;
	}

	public enum ProductType {
		PERISHABLE, NON_PERISHABLE
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", thresholdQuantity=" + thresholdQuantity + ", type=" + type
				+ ", price=" + price + ", quantity=" + quantity + "]";
	}

}
