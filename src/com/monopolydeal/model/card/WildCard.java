package com.monopolydeal.model.card;

import java.awt.*;

public class WildCard extends Card {
    private Color color;
    private int[] price;

    public WildCard(String name, int value) {
        super(name, Card.WILD_CARD, value);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int[] getPrice() {
        return price;
    }

    public void setPrice(int[] price) {
        this.price = price;
    }
}
