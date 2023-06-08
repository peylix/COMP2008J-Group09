package com.monopolydeal.model.card;

public abstract class Card implements com.monopolydeal.Interface.Card {
    public static final int MONEY_CARD = 0;
    public static final int PROPERTY_CARD = 1;
    public static final int WILD_CARD = 2;
    public static final int RENT_CARD = 3;
    public static final int ACTION_CARD = 4;

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

