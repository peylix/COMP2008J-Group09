package com.monopolydeal.view.screen;

import com.monopolydeal.controller.GameController;
import com.monopolydeal.util.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuScreen extends BaseScreen implements ActionListener {
    private JLabel title;
    private JButton startGame;
    private JButton exit;

    public MenuScreen(GameController controller){
        super(controller);
        createContent();
    }

    private void createContent(){
        // title
        title = new JLabel();
        title.setIcon(new ImageIcon(Resources.IMG_LOGO));
        title.setBounds(243, 50, 540, 125);
        add(title);
        // start button
        startGame = new JButton(Resources.BTN_START_TEXT, buttonBG);
        startGame.setBounds(365, 250, 300, 75);
        startGame.setFont(Resources.DEFAULT_FONT);
        startGame.setForeground(Color.LIGHT_GRAY);
        startGame.setHorizontalTextPosition(SwingConstants.CENTER);
        startGame.setBorder(null);
        startGame.setContentAreaFilled(false);
        startGame.addActionListener(this);
        add(startGame);
        // exit button
        exit = new JButton(Resources.BTN_EXIT_TEXT, buttonBG);
        exit.setBounds(365, 360, 300, 75);
        exit.setFont(Resources.DEFAULT_FONT);
        exit.setForeground(Color.LIGHT_GRAY);
        exit.setHorizontalTextPosition(SwingConstants.CENTER);
        exit.setBorder(null);
        exit.setContentAreaFilled(false);
        exit.addActionListener(this);
        add(exit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(startGame)){
            controller.changeScreen(GameController.CHARACTER);
        }else if(e.getSource().equals(exit)){
            System.exit(0);
        }
    }

    @Override
    public void update() {
    }
}

