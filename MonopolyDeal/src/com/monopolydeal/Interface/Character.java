package com.monopolydeal.Interface;

import com.monopolydeal.model.CardSet;

import java.awt.Color;

public interface Character {
    int getAvatarIndex();
    void setAvatarIndex(int avatarIndex);
    int getMoney();
    int getBlockTurns();
    void reduceBlockTurns();
    void block();
    boolean isDoubleRent();
    void setDoubleRent(boolean doubleRent);
    void addMoney(int amount);
    void move();
    int getMoves();
    void resetMoves();
    void removeCardSteal();
    Card getRemoveCardSteal();
    CardSet removeSet(Color color);
    int getRentFee(Color color);
    Card charge(int fee);
    int findProperFee(int fee);
    int fullSet();
}
