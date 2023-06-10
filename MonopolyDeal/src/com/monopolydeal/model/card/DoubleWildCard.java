package com.monopolydeal.model.card;

import java.awt.*;

public class DoubleWildCard extends Card {
    private Color colorUpward;
    private Color colorDownward;
    private int[] priceUpward;
    private int[] priceDownward;

    public DoubleWildCard(String name, int value, Color colorUpward, Color colorDownward,
                          String priceUpward1, String priceUpward2, String priceUpware3,
                          String priceDownward1, String priceDownward2, String priceDownward3) {
        super(name, Card.DOUBLE_WILD_CARD, value);
        this.colorUpward = colorUpward;
        this.colorDownward = colorDownward;
        priceUpward = new int[3];
        priceDownward = new int[3];
        priceUpward[0] = Integer.parseInt(priceUpward1);
        priceUpward[1] = Integer.parseInt(priceUpward2);
        priceUpward[3] = Integer.parseInt(priceUpware3);
        priceDownward[0] = Integer.parseInt(priceDownward1);
        priceDownward[1] = Integer.parseInt(priceDownward2);
        priceDownward[2] = Integer.parseInt(priceDownward3);
    }

    public Color[] getColor() {
        Color[] colors = new Color[2];
        colors[0] = this.colorUpward;
        colors[1] = this.colorDownward;
        return colors;
    }

    public void setColor(Color colorUpward, Color colorDownward) {
        this.colorUpward = colorUpward;
        this.colorDownward = colorDownward;
    }

    public int[] getPriceUpward() {
        return priceUpward;
    }

    public int[] getPriceDownward() {
        return priceDownward;
    }

    public void setPriceUpward(int[] priceUpward) {
        this.priceUpward = priceUpward;
    }

    public void setPriceDownward(int[] priceDownward) {
        this.priceDownward = priceDownward;
    }


}
