package com.monopolydeal.model.card;

import java.awt.*;

public class PropertyCard extends Card {
    private Color color;
    private int[] price;

    public PropertyCard(String name, int value, Color color,
                        String price1, String price2, String price3) {
        super(name, Card.PROPERTY_CARD, value);
        this.color = color;
        if(!price3.equals("n/a")){
            this.price = new int[3];
            this.price[0] = Integer.valueOf(price1);
            this.price[1] = Integer.valueOf(price2);
            this.price[2] = Integer.valueOf(price3);
        }else{
            this.price = new int[2];
            this.price[0] = Integer.valueOf(price1);
            this.price[1] = Integer.valueOf(price2);
        }
    }

    public Color getColor() {
        return color;
    }

    public int[] getPrice() {
        return price;
    }
}

