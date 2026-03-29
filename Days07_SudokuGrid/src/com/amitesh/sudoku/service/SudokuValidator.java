package com.amitesh.sudoku.service;

import com.amitesh.sudoku.model.SudokuBoard;

public interface SudokuValidator {
	
	boolean validateRows(SudokuBoard board);
	boolean validateColumns(SudokuBoard board);
	boolean validateBoxes(SudokuBoard board);

}
