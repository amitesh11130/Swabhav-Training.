package com.amitesh.tictactoe.facade;

import java.util.Scanner;

import com.amitesh.tictactoe.model.Player;
import com.amitesh.tictactoe.service.Game;
import com.amitesh.tictactoe.service.InputHandler;
import com.amitesh.tictactoe.strategy.ComputerMoveStrategy;
import com.amitesh.tictactoe.strategy.HumanMoveStrategy;

public class GameFacade {

	private final Scanner scanner = new Scanner(System.in);
	private final InputHandler input = new InputHandler(scanner);

	public void start() {

		while (true) {

			int mode = input.chooseMode();

			if (mode == 3) {
				System.out.println("Exiting...");
				scanner.close();
				break;
			}

			Player p1 = new Player(input.getName("Player 1"), input.getSymbol(), new HumanMoveStrategy(input));

			Player p2;

			if (mode == 1) {

				String name = input.getName("Player 2");
				char sym = (p1.getSymbol() == 'X') ? 'O' : 'X';
				System.out.println(name + " symbol: " + sym);

				p2 = new Player(name, sym, new HumanMoveStrategy(input));
			} else {
				char sym = (p1.getSymbol() == 'X') ? 'O' : 'X';
				System.out.println("Computer symbol: " + sym);
				p2 = new Player("Computer", sym, new ComputerMoveStrategy());
			}

			new Game(p1, p2).start();
		}
	}
}