package com.monopolydeal.view.screen;

import com.monopolydeal.controller.GameController;
import com.monopolydeal.util.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterScreen extends BaseScreen implements ActionListener {
    private int count = 0;
    private JLabel title;
    private JLabel info;
    private JButton[] characters;
    private JButton back;

    public CharacterScreen(GameController controller) {
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
        info.setText("Player " + (count + 1) + ": Please select your role.");
        info.setBounds(290,160 ,500,30);
        info.setFont(Resources.INFO_FONT);
        info.setForeground(Color.WHITE);
        add(info);

        // character buttons
        characters = new JButton[6];
        for(int i=0;i<characters.length;i++){
            characters[i] = new JButton(Resources.AVATAR_NAME[i],
                    new ImageIcon(Resources.AVATAR_LOGO[i]));
            characters[i].setBorder(null);
            characters[i].setContentAreaFilled(false);
            characters[i].addActionListener(this);
            add(characters[i]);
        }
        characters[0].setBounds(290, 200, 140,128);
        characters[1].setBounds(440, 200, 140,128);
        characters[2].setBounds(590, 200, 140,128);
        characters[3].setBounds(290, 350, 140,128);
        characters[4].setBounds(440, 350, 140,128);
        characters[5].setBounds(590, 350, 140,128);

        // back button
        back = new JButton(Resources.BTN_RETURN_TEXT, buttonBG);
        back.setBounds(365, 490, 300, 75);
        back.setFont(Resources.DEFAULT_FONT);
        back.setForeground(Color.LIGHT_GRAY);
        back.setHorizontalTextPosition(SwingConstants.CENTER);
        back.setBorder(null);
        back.addActionListener(this);
        add(back);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(back)){
            controller.changeScreen(GameController.MENU);
        }else{
            for(int i=0;i<characters.length;i++){
                if(e.getSource().equals(characters[i])){
                    controller.setPlayerInfo(count, i);
                    characters[i].setIcon(new ImageIcon(Resources.IMG_HIDE_LOGO));
                    count++;
                    info.setText("Player " + (count + 1) + ": Please select your avatar.");
                    break;
                }
            }
            if(count == 3){
                controller.changeScreen(GameController.GAME);
            }
        }
    }

    @Override
    public void update() {
        count = 0;
        info.setText("Player " + (count + 1) + ": Please select your avatar.");
        for(int i=0;i<characters.length;i++){
            characters[i].setIcon(new ImageIcon(Resources.AVATAR_LOGO[i]));
        }
    }
}