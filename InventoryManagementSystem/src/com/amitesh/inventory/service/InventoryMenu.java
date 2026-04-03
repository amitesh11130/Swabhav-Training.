package com.amitesh.inventory.service;

import java.util.Scanner;

import com.amitesh.inventory.model.Product;

public class InventoryMenu {

	public static void run(Scanner scanner, InventoryService service) {

		boolean active = true;
		int choice;
		while (active) {
			System.out.println("\n===== INVENTORY MENU =====");
			System.out.println("1. Add Product");
			System.out.println("2. Update product by id");
			System.out.println("3. Delete product by id");
			System.out.println("4. Add Stock");
			System.out.println("5. Remove Stock");
			System.out.println("6. Display Product");
			System.out.println("0. Exit");
			System.out.print("Enter choice: ");

			while (!scanner.hasNextInt()) {
				System.out.println("Invalid input! Enter a number.");
				scanner.next();
			}

			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				service.addProduct(InputHandler.createProductFromUser(scanner));
				break;
			case 2:
				int updateId = InputHandler.readInt(scanner, "Enter Product ID to update: ");
				System.out.println("Enter product details to update:");
				Product updatedProduct = InputHandler.updateProductFromUser(scanner);
				service.updateProduct(updateId, updatedProduct);
				break;

			case 3:
				int deleteId = InputHandler.readInt(scanner, "Enter Product ID to delete: ");
				service.deleteProduct(deleteId);
				break;
			case 4:
				int id = InputHandler.readInt(scanner, "Enter Product ID: ");
				int addQuantity = InputHandler.readInt(scanner, "Enter Quantity to Add: ");
				service.addStock(id, addQuantity);
				break;
			case 5:
				int productid = InputHandler.readInt(scanner, "Enter Product ID: ");
				int removeQuantity = InputHandler.readInt(scanner, "Enter Quantity to Remove: ");

				service.removeStock(productid, removeQuantity);
				break;
			case 6:
				service.displayProduct(scanner);
				break;
			case 0:
				System.out.println("Exiting...");
				scanner.close();
				active = false;
				break;
			default:
				System.out.println("Invalid Choice!");
			}
		}

	}

}
