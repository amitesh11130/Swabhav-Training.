package com.amitesh.sudoku.service;

import java.util.Random;

public class SudokuGenerator {

	private int[][] board = new int[9][9];
	private Random rand = new Random();

	public int[][] generateBoard(int difficulty) {

		fillDiagonal();
		fillRemaining(0, 3);

		switch (difficulty) {
		case 1:
			removeCells(21);
			break;
		case 2:
			removeCells(31);
			break;
		case 3:
			removeCells(41);
			break;
		}

		return board;
	}

	private void fillDiagonal() {
		for (int i = 0; i < 9; i += 3)
			fillBox(i, i);
	}

	private void fillBox(int row, int col) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {

				int num;
				do {
					num = rand.nextInt(9) + 1;
				} while (!isSafe(row + i, col + j, num));

				board[row + i][col + j] = num;
			}
		}
	}

	private boolean isSafe(int row, int col, int num) {

		for (int x = 0; x < 9; x++)
			if (board[row][x] == num || board[x][col] == num)
				return false;

		int startRow = row - row % 3;
		int startCol = col - col % 3;

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (board[startRow + i][startCol + j] == num)
					return false;

		return true;
	}

	private boolean fillRemaining(int i, int j) {

		if (j >= 9 && i < 8) {
			i++;
			j = 0;
		}

		if (i >= 9 && j >= 9)
			return true;

		if (i < 3) {
			if (j < 3)
				j = 3;
		} else if (i < 6) {
			if (j == (i / 3) * 3)
				j += 3;
		} else {
			if (j == 6) {
				i++;
				j = 0;
				if (i >= 9)
					return true;
			}
		}

		for (int num = 1; num <= 9; num++) {

			if (isSafe(i, j, num)) {

				board[i][j] = num;

				if (fillRemaining(i, j + 1))
					return true;

				board[i][j] = 0;
			}
		}

		return false;
	}

	private void removeCells(int count) {

		while (count != 0) {

			int cellId = rand.nextInt(81);

			int i = cellId / 9;
			int j = cellId % 9;

			if (board[i][j] != 0) {
				board[i][j] = 0;
				count--;
			}
		}
	}
}