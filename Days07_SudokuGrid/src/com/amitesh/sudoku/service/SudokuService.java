package com.amitesh.sudoku.service;

import com.amitesh.sudoku.exception.InvalidMoveException;
import com.amitesh.sudoku.model.SudokuBoard;

public class SudokuService{

	private SudokuBoard board;
	private SudokuValidatorImpl validator;

	public SudokuService(SudokuBoard board) {
		this.board = board;
		this.validator = new SudokuValidatorImpl();
	}

	public void makeMove(int row, int col, int value) throws InvalidMoveException {

		if (board.getValue(row, col) != 0) {
			throw new InvalidMoveException("Cell already filled!");
		}

		board.setValue(row, col, value);

		if (!validator.isValid(board)) {
			board.setValue(row, col, 0);
			throw new InvalidMoveException("Invalid move!");
		}
	}

	public boolean isGameCompleted() {
		int[][] grid = board.getGrid();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (grid[i][j] == 0) {
					return false;
				}
			}
		}

		return validator.isValid(board);
	}

	public SudokuBoard getBoard() {
		return board;
	}
}