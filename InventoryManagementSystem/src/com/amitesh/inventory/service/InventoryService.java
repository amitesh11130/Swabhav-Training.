package com.amitesh.inventory.service;

import java.util.List;
import java.util.Scanner;

import com.amitesh.inventory.model.Product;
import com.amitesh.inventory.notification.Notifier;

public class InventoryService {

	private List<Notifier> notifiers;
	private ProductRepository productRepository;
	private ReorderService reorderService;

	public InventoryService(ProductRepository productRepository, ReorderService reorderService,
			List<Notifier> notifiers) {
		this.productRepository = productRepository;
		this.reorderService = reorderService;
		this.notifiers = notifiers;
	}

	public void addProduct(Product product) {
		productRepository.addProduct(product);
		checkAndTriggerAlert(product);
	}

	public void updateProduct(Integer productId, Product updatedProduct) {
		Product existingProduct = productRepository.findByID(productId);

		if (existingProduct == null) {
			System.out.println("Product not found with given ID: " + productId);
			return;
		}

		existingProduct.setName(updatedProduct.getName());
		existingProduct.setPrice(updatedProduct.getPrice());
		existingProduct.setQuantity(updatedProduct.getQuantity());
		existingProduct.setThresholdQuantity(updatedProduct.getThresholdQuantity());
		existingProduct.setType(updatedProduct.getType());

		System.out.println("Product updated successfully: " + existingProduct.getName());

		checkAndTriggerAlert(existingProduct);
	}

	public void deleteProduct(Integer productId) {
		Product product = productRepository.findByID(productId);

		if (product == null) {
			System.out.println("Product not found with given ID: " + productId);
			return;
		}

		productRepository.removeProduct(productId);
		System.out.println("Product deleted successfully: " + product.getName());
	}

	public void addStock(Integer productId, int quantity) {
		Product product = productRepository.findByID(productId);
		if (product == null) {
			System.out.println("Product not found with given ID: " + productId);
			return;
		}
		product.setQuantity(product.getQuantity() + quantity);
		System.out.println("Stock updated: Added " + quantity + " Units of " + product.getName());
		checkAndTriggerAlert(product);
	}

	public void removeStock(Integer productId, int quantity) {
		Product product = productRepository.findByID(productId);
		if (product == null) {
			System.out.println("Product not found with given ID: " + productId);
			return;
		}
		if (product.getQuantity() < quantity) {
			System.out.println("Insufficient stock!");
		}
		if (product.getQuantity() >= quantity) {
			System.out.println("Stock updated: Removed " + quantity + " units of: " + product.getName());
			product.setQuantity(product.getQuantity() - quantity);
			System.out.println("Current stock for " + product.getName() + " is: " + product.getQuantity());
		}

		checkAndTriggerAlert(product);
	}

	private void checkAndTriggerAlert(Product product) {
		if (product.getQuantity() <= product.getThresholdQuantity()) {
			System.out.println("Reorder threshold reached for " + product.getName());
			reorderService.reorder(product);

			for (Notifier notifier : notifiers) {
				notifier.sendNotification("Low stock for product: " + product.getName());
			}
		}
	}

	public void displayProduct(Scanner scanner) {
		Product.ProductType type = null;
		System.out.println("Which type of products do you want to display?");
		System.out.println("1 = PERISHABLE");
		System.out.println("2 = NON_PERISHABLE");
		System.out.println("3 = ALL");

		System.out.print("Enter your choice: ");
		String input = scanner.nextLine().trim();

		switch (input) {
		case "1":
			type = Product.ProductType.PERISHABLE;
			break;
		case "2":
			type = Product.ProductType.NON_PERISHABLE;
			break;
		case "3":
			type = null;
			break;
		default:
			System.out.println("Invalid choice. Please enter 1, 2, or 3.");
			break;
		}

		List<Product> allProduct = productRepository.findAllProduct(type);
		if (allProduct == null || allProduct.isEmpty()) {
			System.out.println("No products found for the selected type.");
			return;
		}
		System.out.println("Displaying products:");
		allProduct.forEach(p -> System.out.println(p));
	}

}
