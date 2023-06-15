package com.monopolydeal.model;

import com.monopolydeal.model.card.Card;
import com.monopolydeal.model.card.DoubleWildCard;
import com.monopolydeal.model.card.PropertyCard;
import com.monopolydeal.model.card.WildCard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class CardSet {
    // It represents a set of cards.
    private ArrayList<Card> set;
    private Color color;

    public CardSet(Color color) {
        this.color = color;
        this.set = new ArrayList<>();
    }

    public Iterator<Card> iterator(){
        return set.iterator();
    }

    public Card getCard(int index){
        return set.get(index);
    }

    public int size(){
        return set.size();
    }

    public Color getColor() {
        return color;
    }

    public void addProperty(Card card){
        set.add(card);
    }

    public void removeProperty(Card card){
        set.remove(card);
    }

    public boolean isFull() {
        if(set.size()==0){
            return false;
        }
        Card card = set.get(0);
        int size = 0;
        if (card instanceof PropertyCard) {
            size = ((PropertyCard) card).getPrice().length;
        }else if (card instanceof WildCard) {
            size = ((WildCard) card).getPrice().length;
        } else if (card instanceof DoubleWildCard) {
            size = ((DoubleWildCard) card).getCurrentPrice().length;
        }
        return set.size() == size;
    }
}
