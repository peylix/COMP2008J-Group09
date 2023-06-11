package com.monopolydeal;

import com.monopolydeal.controller.GameController;
import com.monopolydeal.view.GameFrame;

import java.util.Scanner;

public class LauncherTest {

	public static void main(String[] args) {
		GameController controller = new GameController();
		GameFrame gameFrame = new GameFrame(controller);
		controller.setGameFrame(gameFrame);
		Scanner sc = new Scanner(System.in);
		while (true){
			//input 1 or 2 or 3 find player
			System.out.println("Please input player id. (1 or 2 or 3)");
			String input = sc.next();
			int playerId = 0;
			try {
				playerId = Integer.parseInt(input);
				if (playerId < 1 || playerId > 3){
					System.out.println("Sorry,you input error");
					continue;
				}
			} catch (NumberFormatException e) {
				System.out.println("Sorry,you input error");
				continue;
			}
			GameController.getInfo(playerId-1);
		}
	}
}
