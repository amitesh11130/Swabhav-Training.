package com.amitesh.sudoku.service;

import com.amitesh.sudoku.model.SudokuBoard;

public class SudokuValidatorImpl implements SudokuValidator {

	public boolean isValid(SudokuBoard board) {
		return validateRows(board) && validateColumns(board) && validateBoxes(board);
	}

	public boolean validateRows(SudokuBoard board) {
		for (int[] row : board.getGrid()) {
			if (!check(row))
				return false;
		}
		return true;
	}

	public boolean validateColumns(SudokuBoard board) {
		int[][] grid = board.getGrid();

		for (int col = 0; col < 9; col++) {
			int[] column = new int[9];
			for (int row = 0; row < 9; row++) {
				column[row] = grid[row][col];
			}
			if (!check(column))
				return false;
		}
		return true;
	}

	public boolean validateBoxes(SudokuBoard board) {
		int[][] grid = board.getGrid();

		for (int r = 0; r < 9; r += 3) {
			for (int c = 0; c < 9; c += 3) {

				int[] box = new int[9];
				int index = 0;

				for (int i = r; i < r + 3; i++) {
					for (int j = c; j < c + 3; j++) {
						box[index++] = grid[i][j];
					}
				}

				if (!check(box))
					return false;
			}
		}
		return true;
	}

	private boolean check(int[] arr) {
		boolean[] seen = new boolean[9];

		for (int num : arr) {
			if (num == 0)
				continue;

			if (seen[num - 1])
				return false;
			seen[num - 1] = true;
		}
		return true;
	}
}