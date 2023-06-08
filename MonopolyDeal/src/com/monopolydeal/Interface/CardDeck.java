package com.monopolydeal.Interface;

import com.monopolydeal.model.card.Card;

public interface CardDeck {
    public void add(Card card);
    public Card deal();
    public void shuffle();
    public int size();
}
