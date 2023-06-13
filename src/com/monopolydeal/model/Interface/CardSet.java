package com.monopolydeal.model.Interface;

import java.awt.*;
public interface CardSet {
    Card getCard(int index);
    int size();
    Color getColor();
    boolean isFull();
}
