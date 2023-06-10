package com.monopolydeal.view.screen;

import com.monopolydeal.controller.GameController;
import com.monopolydeal.util.Resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class BaseScreen extends JPanel{
    protected GameController controller;
    protected ImageIcon buttonBG = new ImageIcon(Resources.IMG_BUTTON);

    public BaseScreen(GameController controller){
        this.controller = controller;
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        BufferedImage background = null;
        try {
            background = ImageIO.read(new File(Resources.IMG_BG));
        } catch(Exception e) {
            e.printStackTrace();
        }
        g.drawImage(background, 0, 0, this);
    }


    public abstract void update();

}
