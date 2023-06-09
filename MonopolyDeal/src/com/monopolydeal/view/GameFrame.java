package com.monopolydeal.view;

import com.monopolydeal.controller.GameController;
import com.monopolydeal.view.screen.*;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public static final String TITLE = "Monopoly Deal";
    public static final int WIDTH = 1030;
    public static final int HEIGHT = 758;

    private BaseScreen[] panels;

    public GameFrame(GameController controller){
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        panels = new BaseScreen[4];
        panels[0] = new MenuScreen(controller);
        panels[1] = new CharacterScreen(controller);
        panels[2] = new GameScreen(controller);
        panels[3] = new WinScreen(controller);
        changePanel(GameController.MENU);
        setVisible(true);
    }

    public void changePanel(int index){
        setContentPane(panels[index]);
        panels[index].update();
        pack();
        setLocationRelativeTo(null);
    }
}

