package com.amitesh.tictactoe.model;

import com.amitesh.tictactoe.strategy.MoveStrategy;

public class Player {

	private final String name;
	private final char symbol;
	private final MoveStrategy strategy;

	public Player(String name, char symbol, MoveStrategy strategy) {
		this.name = name;
		this.symbol = symbol;
		this.strategy = strategy;
	}

	public int makeMove(Board board) {
		return strategy.makeMove(board, this);
	}

	public String getName() {
		return name;
	}

	public char getSymbol() {
		return symbol;
	}

}
