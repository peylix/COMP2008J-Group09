package com.monopolydeal.model.card;

import javax.swing.*;

public abstract class Card extends JLabel implements com.monopolydeal.model.Interface.Card {
    public static final int MONEY_CARD = 0;
    public static final int PROPERTY_CARD = 1;
    public static final int WILD_CARD = 2;
    public static final int RENT_CARD = 3;
    public static final int ACTION_CARD = 4;
    public static final int DOUBLE_WILD_CARD = 5;

    protected String name;
    protected int type;
    protected int value;

    public Card(String name, int type, int value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

}
