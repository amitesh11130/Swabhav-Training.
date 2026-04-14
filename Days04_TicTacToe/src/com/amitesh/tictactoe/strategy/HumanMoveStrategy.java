package com.amitesh.tictactoe.strategy;

import com.amitesh.tictactoe.model.Board;
import com.amitesh.tictactoe.model.Player;
import com.amitesh.tictactoe.service.InputHandler;

public class HumanMoveStrategy implements MoveStrategy {

	private final InputHandler input;

	public HumanMoveStrategy(InputHandler input) {
		this.input = input;
	}

	@Override
	public int makeMove(Board board, Player player) {
		return input.getPosition(player.getName());
	}

}
