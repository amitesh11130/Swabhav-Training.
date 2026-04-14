package com.amitesh.tictactoe.service;

import com.amitesh.tictactoe.model.Board;
import com.amitesh.tictactoe.model.Player;

public class Game {

    private final Board board = new Board();
    private final Player p1, p2;

    public Game(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void start() {
        Player current = p1;

        while (true) {
            printBoard();

            int pos = current.makeMove(board);

            if (!board.placeMove(pos, current.getSymbol())) {
                System.out.println("Position already taken!");
                continue;
            }

            if (board.checkWinner(current.getSymbol())) {
                printBoard();
                System.out.println(current.getName() + " wins!");
                return;
            }

            if (board.isFull()) {
                printBoard();
                System.out.println("Draw!");
                return;
            }

            current = (current == p1) ? p2 : p1;
        }
    }


    private void printBoard() {
        char[][] b = board.getGrid();
        int pos = 1;

        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char val = (b[i][j] == ' ') ? (char)(pos + '0') : b[i][j];
                System.out.print("| " + val + " ");
                pos++;
            }
            System.out.println("|");
            System.out.println("-------------");
        }
    }
}