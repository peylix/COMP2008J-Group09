package com.monopolydeal.model;

import com.monopolydeal.model.card.Card;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
    private ArrayList<Card> cards;

    public CardDeck() {
        cards = new ArrayList<>();
    }

    public void add(Card card){
        cards.add(card);
    }

    public Card deal(){
        return cards.remove(0);
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public int size(){
        return cards.size();
    }

}
