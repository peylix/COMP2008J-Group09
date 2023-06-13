package com.monopolydeal;

import com.monopolydeal.controller.GameController;
import com.monopolydeal.model.Character;
import com.monopolydeal.view.GameFrame;

import java.util.Scanner;
import java.util.stream.Collectors;

public class LauncherTest {

	public static void main(String[] args) {
		GameController controller = new GameController();
		GameFrame gameFrame = new GameFrame(controller);
		controller.setGameFrame(gameFrame);
		Scanner sc = new Scanner(System.in);
		while (true){
			//Enter the player you want to query enter 1 or 2 or 3
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
			getInfo(playerId-1,controller);
		}
	}


	public static void getInfo(int i,GameController controller){
		Character character = controller.characters[i];
		System.out.print("===player:"+(i+1));
		System.out.print("  money:"+character.getMoney()+",money cards:"+character.getDollars().size()+",detail:"+character.getDollars());
		System.out.println("    hand cards:"+character.getHand().size()+",detail:"+(character.getHand().stream().map(card -> card.getName()).collect(Collectors.toList())));

	}
}
