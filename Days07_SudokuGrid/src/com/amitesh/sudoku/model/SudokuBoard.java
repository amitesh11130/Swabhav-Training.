package com.amitesh.sudoku.model;

public class SudokuBoard {

	private int[][] grid;

	public SudokuBoard(int[][] grid) {
		this.grid = grid;
	}

	public int getValue(int row, int col) {
		return grid[row][col];
	}

	public void setValue(int row, int col, int value) {
		grid[row][col] = value;
	}

	public int[][] getGrid() {
		return grid;
	}

//	public void display() {
//		for (int[] row : grid) {
//			for (int val : row) {
//				System.out.print(val + " ");
//			}
//			System.out.println();
//		}
//	}
	public void display() {

		System.out.print("    ");
		for (int i = 0; i < 9; i++) {
			System.out.print(i + " ");
		}
		System.out.println();

		System.out.println("   -------------------");

		for (int i = 0; i < 9; i++) {

			System.out.print(i + " | ");

			for (int j = 0; j < 9; j++) {

				System.out.print(grid[i][j] + " ");
			}

			System.out.println("|");

		}

		System.out.println("   -------------------");
	}
}