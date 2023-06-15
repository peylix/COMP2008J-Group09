package com.monopolydeal.model.card;

public class ActionCard extends Card {
    public static final int DRAW = 0;
    public static final int STEAL1 = 1;
    public static final int STEAL3 = 2;
    public static final int TRADE = 3;
    public static final int TAKE_MONEY = 4;
    public static final int DOUBLE_RENT = 5;
    public static final int BIRTHDAY = 6;
    public static final int BLOCK = 7;
    public static final int HOUSE = 8;
    public static final int HOTEL = 9;

    private int action;

    public ActionCard(String name, int value, int action) {
        super(name, Card.ACTION_CARD, value);
        this.action = action;
    }

    public int getAction() {
        return action;
    }
}

