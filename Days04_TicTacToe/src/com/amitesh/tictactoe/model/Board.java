package com.amitesh.tictactoe.model;

public class Board {

	private final char[][] grid = new char[3][3];

	public Board() {
		initBoard();
	}

	public void initBoard() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				grid[i][j] = ' ';
	}

	public boolean placeMove(int pos, char symbol) {
		int r = (pos - 1) / 3;
		int c = (pos - 1) % 3;

		if (grid[r][c] != ' ')
			return false;

		grid[r][c] = symbol;
		return true;
	}

	public char[][] getGrid() {
		return grid;
	}
	
	public boolean checkWinner(char s) {
	    for (int i = 0; i < 3; i++) {
	        if (grid[i][0] == s && grid[i][1] == s && grid[i][2] == s) return true;
	        if (grid[0][i] == s && grid[1][i] == s && grid[2][i] == s) return true;
	    }

	    return (grid[0][0] == s && grid[1][1] == s && grid[2][2] == s)
	        || (grid[0][2] == s && grid[1][1] == s && grid[2][0] == s);
	}

	public boolean isFull() {
		for (char[] row : grid)
			for (char c : row)
				if (c == ' ')
					return false;
		return true;
	}
}
