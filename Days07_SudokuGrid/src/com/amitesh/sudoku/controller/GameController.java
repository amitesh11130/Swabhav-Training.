package com.amitesh.sudoku.controller;

import java.util.Scanner;

import com.amitesh.sudoku.exception.InvalidMoveException;
import com.amitesh.sudoku.model.SudokuBoard;
import com.amitesh.sudoku.service.SudokuGenerator;
import com.amitesh.sudoku.service.SudokuService;

public class GameController {

	private Scanner scanner = new Scanner(System.in);

	public void startGame() {

		while (true) {

			SudokuGenerator generator = new SudokuGenerator();

			int difficulty = chooseDifficulty();

			int[][] grid = generator.generateBoard(difficulty);

			SudokuBoard board = new SudokuBoard(grid);
			SudokuService service = new SudokuService(board);

			board.display();

			while (true) {
				try {
					int row = getInput("Row (0-8): ", 0, 8);
					int col = getInput("Col (0-8): ", 0, 8);
					int val = getInput("Value (1-9): ", 1, 9);

					service.makeMove(row, col, val);

					System.out.println("Valid move!");
					board.display();

					if (service.isGameCompleted()) {
						System.out.println("\n Game Over! You Win!");
						break;
					}

				} catch (InvalidMoveException e) {
					System.out.println(e.getMessage());
				}
			}

			String choice;

			while (true) {
				System.out.print("Do you want to play again? (yes/no): ");
				choice = scanner.next().toLowerCase();

				if (choice.equals("yes") || choice.equals("no")) {
					break;
				} else {
					System.out.println("Please enter yes or no only!");
				}
			}

			if (!choice.equals("yes")) {
				System.out.println("Thanks for playing!");
				break;
			}
		}
	}

	private int chooseDifficulty() {
		System.out.println("==Choose Difficulty==\n---------------------------\n1 Easy\n2 Medium\n3 Hard");

		while (true) {
			if (scanner.hasNextInt()) {
				int choice = scanner.nextInt();
				if (choice >= 1 && choice <= 3)
					return choice;
			} else
				scanner.next();

			System.out.println("Invalid choice please enter 1 to 3!");
		}
	}

	private int getInput(String msg, int min, int max) {
		while (true) {
			System.out.print(msg);

			if (scanner.hasNextInt()) {
				int val = scanner.nextInt();
				if (val >= min && val <= max)
					return val;
			} else
				scanner.next();

			System.out.println("Invalid input!");
		}
	}
}
