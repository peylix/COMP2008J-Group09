package com.monopolydeal.model;

import com.monopolydeal.model.CardSet;
import com.monopolydeal.model.card.Card;
import com.monopolydeal.model.card.PropertyCard;
import com.monopolydeal.model.card.WildCard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Character {

    private int avatarIndex = 0;
    private int money = 0;
    private int blockTurns = 0;
    private int moves = 3;
    private boolean doubleRent = false;
    private ArrayList<CardSet> properties;
    private ArrayList<Card> hand;

    public Character() {
        properties = new ArrayList<>();
        hand = new ArrayList<>();
    }

    public int getAvatarIndex() {
        return avatarIndex;
    }

    public void setAvatarIndex(int avatarIndex) {
        this.avatarIndex = avatarIndex;
    }

    public int getMoney() {
        return money;
    }

    public int getBlockTurns() {
        return blockTurns;
    }

    public void reduceBlockTurns(){
        blockTurns--;
    }

    public void block(){
        blockTurns += 2;
    }

    public boolean isDoubleRent() {
        return doubleRent;
    }

    public void setDoubleRent(boolean doubleRent) {
        this.doubleRent = doubleRent;
    }

    public void addMoney(int amount){
        money += amount;
    }

    public void move() {
        moves--;
    }

    public int getMoves() {
        return moves;
    }

    public void resetMoves(){
        moves = 3;
    }

    public ArrayList<CardSet> getProperties() {
        return properties;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void removeCard(Card card){
        hand.remove(card);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public boolean addProperty(Card card){
        Color color = null;
        if(card instanceof PropertyCard){
            color = ((PropertyCard) card).getColor();
        }else if(card instanceof WildCard){
            color = ((WildCard) card).getColor();
        }
        CardSet set = null;
        for(CardSet property: properties){
            if(property.getColor().equals(color)){
                set = property;
                break;
            }
        }
        if(set == null){
            set = new CardSet(color);
            properties.add(set);
        }
        if(set.isFull()){
            return false;
        }
        set.addProperty(card);
        return true;
    }

    public void removeProperty(Card card){
        Color color = null;
        if(card instanceof PropertyCard){
            color = ((PropertyCard) card).getColor();
        }else if(card instanceof WildCard){
            color = ((WildCard) card).getColor();
        }
        Iterator<CardSet> iterator = properties.iterator();
        while (iterator.hasNext()){
            CardSet set = iterator.next();
            if(set.getColor().equals(color)){
                set.removeProperty(card);
            }
            if (set.size()==0) {
                iterator.remove();
            }
        }
    }

    public void addSet(CardSet set){
        boolean exist = false;
        for(CardSet property: properties){
            if(property.getColor().equals(set.getColor())){
                exist = true;
                for(int i=0;i<set.size();i++){
                    property.addProperty(set.getCard(i));
                }
                break;
            }
        }
        if(!exist){
            properties.add(set);
        }
    }

    public CardSet removeSet(Color color){
        for(int i=0;i<properties.size();i++){
            if(properties.get(i).getColor().equals(color)){
                return properties.remove(i);
            }
        }
        return null;
    }

    public int getRentFee(Color color){
        int fee = 0;
        for(int i=0;i<properties.size();i++){
            CardSet set = properties.get(i);
            if(set.getColor().equals(color)) {
                Card card = set.getCard(0);
                if(card instanceof PropertyCard){
                    fee = ((PropertyCard)card).getPrice()[set.size()-1];
                }else if(card instanceof WildCard){
                    fee = ((WildCard)card).getPrice()[set.size()-1];
                }
                break;
            }
        }
        return fee;
    }

    public Card charge(int fee){
        if(money>=fee){
            money-=fee;
            return null;
        }
        Iterator<CardSet> setIterator = properties.iterator();
        while(setIterator.hasNext()){
            CardSet set = setIterator.next();
            Iterator<Card> cardIterator = set.iterator();
            while(cardIterator.hasNext()){
                Card card = cardIterator.next();
                if(card.getValue()==fee){
                    return card;

                }
            }
        }
        return null;
    }

    public int fullSet(){
        int count = 0;
        Iterator<CardSet> iterator = properties.iterator();
        while(iterator.hasNext()){
            if(iterator.next().isFull()){
                count++;
            }
        }
        return count;
    }
}

