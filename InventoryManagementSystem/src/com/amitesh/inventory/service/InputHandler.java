package com.amitesh.inventory.service;

import java.util.Scanner;

import com.amitesh.inventory.model.Product;

public class InputHandler {

	public static Product createProductFromUser(Scanner scanner) {
		int id = readInt(scanner, "Enter Product ID: ");
		String name = readString(scanner, "Enter Product Name: ");
		int thresholdQuantity = readInt(scanner, "Enter Threshold Quantity: ");
		double price = readDouble(scanner, "Enter Price: ");
		int quantity = readInt(scanner, "Enter Quantity: ");

		Product.ProductType type = readProductType(scanner);

		return new Product(id, name, thresholdQuantity, type, price, quantity);
	}

	public static Product updateProductFromUser(Scanner scanner) {
		String name = readString(scanner, "Enter Product Name to update: ");
		int thresholdQuantity = readInt(scanner, "Enter Threshold Quantity to update: ");
		double price = readDouble(scanner, "Enter Price to update: ");
		int quantity = readInt(scanner, "Enter Quantity to update: ");

		Product.ProductType type = readProductType(scanner);

		return new Product(name, thresholdQuantity, type, price, quantity);
	}

	public static int readInt(Scanner scanner, String message) {
		while (true) {
			System.out.print(message);
			if (scanner.hasNextInt()) {
				int value = scanner.nextInt();
				scanner.nextLine();
				if (value > 0) {
					return value;
				}
				System.out.println("Value cannot be negative or Zero.");
			} else {
				System.out.println("Invalid input. Please enter a valid integer.");
				scanner.next();
			}
		}
	}

	public static double readDouble(Scanner scanner, String message) {

		while (true) {
			System.out.print(message);
			if (scanner.hasNextDouble()) {
				double value = scanner.nextDouble();
				scanner.nextLine();
				if (value > 0) {
					return value;
				}
				System.out.println("Value cannot be negative or Zero.");
			} else {
				System.out.println("Invalid input. Please enter a valid decimal number.");
				scanner.next();
			}

		}
	}

	public static String readString(Scanner scanner, String message) {

		while (true) {
			System.out.print(message);
			String input = scanner.nextLine().trim();

			if (input.isEmpty()) {
				System.out.println("Input cannot be empty.");
				continue;
			}
			if (!input.matches("[a-zA-Z ]+")) {
				System.out.println("Invalid name. Only letters and spaces are allowed.");
				continue;
			}

			return input.replaceAll("\\s+", " ");
		}
	}

	public static Product.ProductType readProductType(Scanner scanner) {
		System.out.print("Enter Product Type (1 = PERISHABLE, 2 = NON_PERISHABLE): ");
		while (true) {

			String input = scanner.nextLine().trim();
			if ("1".equals(input))
				return Product.ProductType.PERISHABLE;
			if ("2".equals(input))
				return Product.ProductType.NON_PERISHABLE;

			System.out.println("Invalid choice. Try again.");
		}
	}
}
