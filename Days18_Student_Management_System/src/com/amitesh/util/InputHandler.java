package com.amitesh.util;

import java.util.Scanner;

import com.amitesh.model.Student;

public class InputHandler {

	public static int getValidIntInput(Scanner scanner, String message) {
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

	public static Student getStudent(Scanner scanner, String message) {
		System.out.println(message);

		int id = getValidIntInput(scanner, "Enter Student ID: ");
		String name = getValidStringInput(scanner, "Enter Name: ");
		int age = getValidIntInput(scanner, "Enter Age: ");
		int branchid = getValidIntInput(scanner, "Enter Branch ID: ");

		return new Student(id, name, age, branchid);
	}

	public static String getValidStringInput(Scanner scanner, String message) {
		while (true) {
			System.out.print(message);
			String input = scanner.nextLine().trim();

			if (input.isEmpty()) {
				System.out.println("Input cannot be empty.");
				continue;
			}
			if (!input.matches("[a-zA-Z ]+")) {
				System.out.println("Invalid name. Only letters are allowed.");
				continue;
			}

			return input.replaceAll("\\s+", " ");
		}
	}

	public static double getValidDoubleInput(Scanner scanner, String message) {
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

}
