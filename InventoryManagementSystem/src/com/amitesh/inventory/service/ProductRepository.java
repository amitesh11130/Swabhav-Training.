package com.amitesh.inventory.service;

import java.util.List;
import com.amitesh.inventory.model.Product;

public interface ProductRepository {

	void addProduct(Product product);

	void removeProduct(Integer productId);

	Product findByID(Integer productId);

	List<Product> findAllProduct(Product.ProductType type);
}
