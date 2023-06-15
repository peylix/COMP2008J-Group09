package com.monopolydeal.model.card;

import java.awt.*;

public class DoubleWildCard extends Card {
    // A plain old Java object for storing data of a double wild card.
    private Color colorUpward;
    private Color colorDownward;
    private int[] priceUpward;
    private int[] priceDownward;
    private Color currentColor;
    private int[] currentPrice;

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
        priceUpward[2] = Integer.parseInt(priceUpware3);
        priceDownward[0] = Integer.parseInt(priceDownward1);
        priceDownward[1] = Integer.parseInt(priceDownward2);
        priceDownward[2] = Integer.parseInt(priceDownward3);
    }

    public Color getColorUpward() {
        return colorUpward;
    }

    public Color getColorDownward() {
        return colorDownward;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setColor(Color colorUpward, Color colorDownward) {
        this.colorUpward = colorUpward;
        this.colorDownward = colorDownward;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public int[] getPriceUpward() {
        return priceUpward;
    }

    public int[] getPriceDownward() {
        return priceDownward;
    }

    public int[] getCurrentPrice() {
        return currentPrice;
    }

    public void setPriceUpward(int[] priceUpward) {
        this.priceUpward = priceUpward;
    }

    public void setPriceDownward(int[] priceDownward) {
        this.priceDownward = priceDownward;
    }

    public void setCurrentPrice(int[] currentPrice) {
        this.currentPrice = currentPrice;
    }


}
