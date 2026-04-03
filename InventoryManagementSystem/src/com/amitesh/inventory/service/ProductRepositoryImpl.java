package com.amitesh.inventory.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amitesh.inventory.model.Product;

public class ProductRepositoryImpl implements ProductRepository {

	Map<Integer, Product> inventorys = new HashMap<Integer, Product>();

	@Override
	public void addProduct(Product product) {
		if (inventorys.containsKey(product.getId())) {
			System.out.println("Error: Product with this ID already exists.");
			return;
		}
		inventorys.put(product.getId(), product);

		System.out.println("Product added successfully");

	}

	@Override
	public void removeProduct(Integer productId) {
		inventorys.remove(productId);
	}

	@Override
	public Product findByID(Integer productId) {
		return inventorys.get(productId);
	}

	@Override
	public List<Product> findAllProduct(Product.ProductType type) {
		return inventorys.values().stream().filter(p -> type == null || p.getType() == type).toList();
	}
}
