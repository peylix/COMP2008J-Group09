package com.monopolydeal.view.screen;

import com.monopolydeal.controller.GameController;
import com.monopolydeal.util.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinScreen extends BaseScreen implements ActionListener {
    private JLabel title;
    private JLabel info;
    private JButton newGame;
    private JButton exit;

    public WinScreen(GameController controller) {
        super(controller);
        createContent();
    }

    private void createContent(){
        // title
        title = new JLabel();
        title.setIcon(new ImageIcon(Resources.IMG_LOGO));
        title.setBounds(243, 50, 540, 125);
        add(title);

        // info
        info = new JLabel();
        info.setBounds(365,200 ,500,30);
        info.setFont(Resources.INFO_FONT);
        info.setForeground(Color.WHITE);
        add(info);

        // start button
        newGame = new JButton(Resources.BTN_NEW_GAME_TEXT, buttonBG);
        newGame.setBounds(365, 250, 300, 75);
        newGame.setFont(Resources.DEFAULT_FONT);
        newGame.setForeground(Color.LIGHT_GRAY);
        newGame.setHorizontalTextPosition(SwingConstants.CENTER);
        newGame.setBorder(null);
        newGame.setContentAreaFilled(false);
        newGame.addActionListener(this);
        add(newGame);

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

    public void update(){
        info.setText("Congratulation! "
                + Resources.AVATAR_NAME[controller.getWinner().getAvatarIndex()] + " Wins!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(newGame)){
            controller.changeScreen(GameController.CHARACTER);
            controller.reset();
        }else if(e.getSource().equals(exit)){
            System.exit(0);
        }
    }
}
