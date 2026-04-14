package com.amitesh.tictactoe.strategy;

import com.amitesh.tictactoe.model.Board;
import com.amitesh.tictactoe.model.Player;

public interface MoveStrategy {
	int makeMove(Board board, Player player);
}
