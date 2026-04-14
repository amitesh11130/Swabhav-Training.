package com.amitesh.tictactoe;

import com.amitesh.tictactoe.facade.GameFacade;

public class TicTacToeGame {

    public static void main(String[] args) {
        new GameFacade().start();
    }
}