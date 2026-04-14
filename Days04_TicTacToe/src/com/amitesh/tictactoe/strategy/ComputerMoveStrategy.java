package com.amitesh.tictactoe.strategy;

import com.amitesh.tictactoe.model.Board;
import com.amitesh.tictactoe.model.Player;

public class ComputerMoveStrategy implements MoveStrategy{

	@Override
	public int makeMove(Board board, Player computer) {
		char computerSymbol = computer.getSymbol();
        char humanSymbol = (computerSymbol == 'X') ? 'O' : 'X';

        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int i = 1; i <= 9; i++) {
            int row = (i - 1) / 3;
            int col = (i - 1) % 3;

            if (board.getGrid()[row][col] == ' ') {

                board.getGrid()[row][col] = computerSymbol;

                int score = minimax(board, false, computerSymbol, humanSymbol, 0);

                board.getGrid()[row][col] = ' ';

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }

        System.out.println("Computer chose: " + bestMove);
        return bestMove;
    }

    private int minimax(Board board, boolean isMax, char ai, char human, int depth) {

        if (board.checkWinner(ai)) return 10 - depth;
        if (board.checkWinner(human)) return depth - 10;
        if (board.isFull()) return 0;

        int best = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 1; i <= 9; i++) {
            int r = (i - 1) / 3;
            int c = (i - 1) % 3;

            if (board.getGrid()[r][c] == ' ') {

                board.getGrid()[r][c] = isMax ? ai : human;

                int score = minimax(board, !isMax, ai, human, depth + 1);

                board.getGrid()[r][c] = ' ';

                if (isMax)
                    best = Math.max(best, score);
                else
                    best = Math.min(best, score);
            }
        }

        return best;
    }

}
