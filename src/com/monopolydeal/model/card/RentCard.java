package com.monopolydeal.model.card;

import java.awt.*;

public class RentCard extends Card {
    private Color primary;
    private Color secondary;

    public RentCard(String name, int value, Color primary, Color secondary) {
        super(name, Card.RENT_CARD, value);
        this.primary = primary;
        this.secondary = secondary;
    }

    public Color getPrimary() {
        return primary;
    }

    public Color getSecondary() {
        return secondary;
    }
}
