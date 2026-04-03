package com.amitesh.inventory.service;

import com.amitesh.inventory.model.Product;

public class ReorderService {

	public void reorder(Product product) {
		System.out.println("Reorder triggered for: " + product.getName());
		System.out
				.println("Reorder placed for " + product.getThresholdQuantity() * 2 + " units of " + product.getName());
	}

}
