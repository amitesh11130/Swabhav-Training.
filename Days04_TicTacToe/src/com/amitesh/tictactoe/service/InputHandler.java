package com.amitesh.tictactoe.service;

import java.util.Scanner;

import com.amitesh.tictactoe.exception.InvalidInputException;
import com.amitesh.tictactoe.util.Validator;

public class InputHandler {

	private final Scanner scanner;

	public InputHandler(Scanner scanner) {
		this.scanner = scanner;
	}

	public int chooseMode() {
		while (true) {
			System.out.println("\nPlease choose a game mode:");
			System.out.println("1. Human vs Human");
			System.out.println("2. Human vs Computer");
			System.out.println("3. Exit");

			if (!scanner.hasNextInt()) {
				System.out.println("Invalid input! Please enter a number between 1 and 3.");
				scanner.next();
				continue;
			}

			int choice = scanner.nextInt();
			scanner.nextLine();

			if (choice >= 1 && choice <= 3)
				return choice;

			System.out.println("Invalid choice! Please select 1, 2, or 3.");
		}
	}

	public String getName(String label) {
		while (true) {
			System.out.print("Enter " + label + " name: ");
			String name = scanner.nextLine();

			try {
				Validator.validateName(name);
				return name.replaceAll("\\s+", " ");
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public char getSymbol() {
		while (true) {
			System.out.print("Choose symbol (X/O): ");
			String input = scanner.nextLine().toUpperCase();

			if (input.length() == 1) {
				char c = input.charAt(0);
				if (c == 'X' || c == 'O')
					return c;
			}

			System.out.println("Invalid symbol!");
		}
	}

	public int getPosition(String name) {
		while (true) {
			System.out.print(name + " choose (1-9): ");

			if (!scanner.hasNextInt()) {
				System.out.println("Invalid input!");
				scanner.next();
				continue;
			}

			int pos = scanner.nextInt();
			scanner.nextLine();

			if (pos >= 1 && pos <= 9)
				return pos;

			System.out.println("Enter 1-9 only!");
		}
	}
}