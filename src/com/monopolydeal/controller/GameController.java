package com.monopolydeal.controller;

import com.monopolydeal.model.CardDeck;
import com.monopolydeal.model.CardSet;
import com.monopolydeal.model.Character;
import com.monopolydeal.model.card.Card;
import com.monopolydeal.model.card.DoubleWildCard;
import com.monopolydeal.model.card.PropertyCard;
import com.monopolydeal.model.card.WildCard;
import com.monopolydeal.util.DeckReader;
import com.monopolydeal.view.CardComponent;
import com.monopolydeal.view.GameFrame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class GameController{
    public static boolean isTest = false;
    private static final int WIN_AMOUNT = 50;
    public static final int CHARACTER_AMOUNT = 3;
    public static final int INIT_CARD_AMOUNT = 5;
    public static final int NEW_CARD_AMOUNT = 2;

    public static final int MENU = 0;
    public static final int CHARACTER = 1;
    public static final int GAME = 2;
    public static final int WIN = 3;

    private GameFrame gameFrame;
    private CardDeck deck;
    public static Character[] characters;
    private boolean[] steal1;
    private boolean[] steal3;
    private Character currentCharacter;
    private Character winner;
    private int turn = 0;

    public GameController() {
        reset();
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public void changeScreen(int index){
        gameFrame.changePanel(index);
    }

    public Character getWinner(){
        return winner;
    }

    public Character getCharacter(int index){
        return characters[index];
    }

    public boolean getSteal1Selected(int index){
        return steal1[index];
    }

    public boolean getSteal3Selected(int index){
        return steal3[index];
    }

    public Character getCurrentCharacter() {
        return currentCharacter;
    }

    public void setPlayerInfo(int index, int avatarIndex){
        characters[index].setAvatarIndex(avatarIndex);
    }

    public int getTurn() {
        return turn;
    }

    public boolean couldMove(){
        return currentCharacter.getMoves() > 0;
    }

    public void depositCard(Card card){
        currentCharacter.addMoney(card.getValue());
        currentCharacter.removeCard(card);
        currentCharacter.move();
    }

    public void addProperty(Card card){
       if(currentCharacter.addProperty(card)){
           currentCharacter.removeCard(card);
           currentCharacter.move();
       }
    }

    public void addWildProperty(Card card){
        if(currentCharacter.addProperty(card)){
            currentCharacter.removeCard(card);
            currentCharacter.move();
        }
    }

    public void rent(Card card, Color color1, Color color2){
        Character opponent = characters[getOpponent()];
        int rentFee1 = currentCharacter.getRentFee(color1);
        int rentFee2 = currentCharacter.getRentFee(color2);
        if(currentCharacter.isDoubleRent()){
            rentFee1 = rentFee1 * 2;
            rentFee2 = rentFee2 * 2;
        }
        int originalMoney = opponent.getMoney();
        int rent1 = opponent.charge(rentFee1);
        int rent2 = opponent.charge(rentFee2);
        int currentMoney = opponent.getMoney();
        if(originalMoney - currentMoney == rent1){
            currentCharacter.addMoney(rent1);
            currentCharacter.setDoubleRent(false);
            currentCharacter.removeCard(card);
            currentCharacter.move();
            return;
        } else if(originalMoney - currentMoney == rent2) {
            currentCharacter.addMoney(rent2);
            currentCharacter.setDoubleRent(false);
            currentCharacter.removeCard(card);
            currentCharacter.move();
            return;
        }
    }

    public void draw(Card currentCard){
        for(int i=0;i<NEW_CARD_AMOUNT;i++){
            if(deck.size()>0){
                currentCharacter.addCard(deck.deal());
            }
        }
        currentCharacter.removeCard(currentCard);
        currentCharacter.move();
    }

    public void steal1(Card steal1){
        Character opponent = characters[getOpponent()];
        Card removedCard = opponent.getRemoveCardSteal();
        opponent.removeCardSteal();
        currentCharacter.addCard(removedCard);
        currentCharacter.removeCard(steal1);
        currentCharacter.move();
    }

    private void steal3Unselect(){
        for(int i=0;i<CHARACTER_AMOUNT;i++){
            steal3[i] = false;
        }
    }

    public void steal3(int index, Color color, Card steal3){
        CardSet set = characters[index].removeSet(color);
        currentCharacter.addSet(set);
        currentCharacter.removeCard(steal3);
        currentCharacter.move();
        steal3Unselect();
    }

    public void trade(Card trade){
        currentCharacter.removeCard(trade);
        Card swapCard = currentCharacter.getHand().get(0);
        currentCharacter.removeCard(swapCard);
        Character opponent = characters[getOpponent()];
        Card removedCard = opponent.getRemoveCardSteal();
        opponent.removeCardSteal();
        opponent.addCard(swapCard);
        currentCharacter.addCard(removedCard);
        currentCharacter.move();
    }

    public void takeMoney(Card card){
        Character opponent = characters[getOpponent()];
        int fee = 5;
        int originalMoney = opponent.getMoney();
        int rent = opponent.charge(fee);
        int currentMoney = opponent.getMoney();
        if(originalMoney - currentMoney == rent){
            currentCharacter.addMoney(fee);
            currentCharacter.removeCard(card);
            currentCharacter.move();
            return;
        }
    }

    public void birthday(Card card){
        if(deck.size()>0){
            currentCharacter.addCard(deck.deal());
        }
        currentCharacter.addMoney(2);
        Character opponent = characters[getOpponent()];
        opponent.charge(2);
        currentCharacter.removeCard(card);
        currentCharacter.move();
    }

    public void doubleRent(Card card){
        currentCharacter.setDoubleRent(true);
        currentCharacter.removeCard(card);
        currentCharacter.move();
    }

    private int getOpponent(){
        int index = 0;
        for(int i=0;i<CHARACTER_AMOUNT;i++){
            if(currentCharacter.equals(characters[i])){
                index = (i + 1) % CHARACTER_AMOUNT;
                break;
            }
        }
        return index;
    }

    public void block(Card card){
        characters[getOpponent()].block();
        currentCharacter.removeCard(card);
        currentCharacter.move();
    }

    private void dealCard(){
        int maxDraw = 0;
        for(int i=0;i<CHARACTER_AMOUNT;i++){
            if(characters[i].getHand().size()==0){
                maxDraw = INIT_CARD_AMOUNT;
            }else{
                maxDraw = NEW_CARD_AMOUNT;
            }
            for(int j=0;j<maxDraw;j++){
                if(deck.size()>0) {
                    characters[i].addCard(deck.deal());
                }
            }
        }
    }

    private boolean checkWin(){
        if(currentCharacter.getMoney()>=WIN_AMOUNT){
            winner = currentCharacter;
            return true;
        }
        if(currentCharacter.fullSet()==3){
            winner = currentCharacter;
            return true;
        }
        return false;
    }

    public void nextTurn(){
        if(checkWin()){
            gameFrame.changePanel(WIN);
            return;
        }
        if(currentCharacter.getBlockTurns()>0){
            currentCharacter.reduceBlockTurns();
        }
        turn = (turn + 1) % CHARACTER_AMOUNT;
        if(turn == 0){
            dealCard();
        }
        currentCharacter = characters[turn];
        currentCharacter.resetMoves();
        if(currentCharacter.getHand().size() > 7){
            int record = 0;
            for(int i = 0; i < currentCharacter.getHand().size() - 6; i++){
                Card removedCard = currentCharacter.getHand().get(record);
                currentCharacter.removeCard(removedCard);
                record++;
            }
        }
    }


    public Color setHouse(Card card, Card thisCard) {
        if (currentCharacter.house) {
            return null;
        }
        if (card instanceof PropertyCard) {
            currentCharacter.houseColor = ((PropertyCard) card).getColor();
            if (((PropertyCard) card).getColor().equals(new Color(89,12,56)) || ((PropertyCard) card).getColor().equals(new Color(96,96,96))) {
                return null;
            }
            int[] price = ((PropertyCard) card).getPrice();
            for (int i = 0; i < price.length; i++) {
                price[i] = price[i] + 2;
            }
            ((PropertyCard) card).setPrice(price);
        } else if (card instanceof WildCard) {
            currentCharacter.houseColor = ((WildCard) card).getColor();
            int[] price = ((WildCard) card).getPrice();
            for (int i = 0; i < price.length; i++) {
                price[i] = price[i] + 2;
            }
            ((WildCard) card).setPrice(price);
        } else if (card instanceof DoubleWildCard) {
            currentCharacter.houseColor = ((DoubleWildCard) card).getCurrentColor();
            int[] price = ((DoubleWildCard) card).getCurrentPrice();
            for (int i = 0; i < price.length; i++) {
                price[i] = price[i] + 2;
            }
            ((DoubleWildCard) card).setCurrentPrice(price);
        }
        currentCharacter.house = true;
        currentCharacter.removeCard(thisCard);
        return currentCharacter.houseColor;
    }

    public Color setHotel(Card card, Card thisCard) {
        if (!currentCharacter.house) {
            return null;
        }
        if (currentCharacter.hotel) {
            return null;
        }
        if (card instanceof PropertyCard) {
            currentCharacter.hotelColor = ((PropertyCard) card).getColor();
            if (((PropertyCard) card).getColor().equals(new Color(89,12,56)) || ((PropertyCard) card).getColor().equals(new Color(96,96,96))) {
                return null;
            }
            int[] price = ((PropertyCard) card).getPrice();
            for (int i = 0; i < price.length; i++) {
                price[i] = price[i] + 2;
            }
            ((PropertyCard) card).setPrice(price);
        } else if (card instanceof WildCard) {
            currentCharacter.hotelColor = ((WildCard) card).getColor();
            int[] price = ((WildCard) card).getPrice();
            for (int i = 0; i < price.length; i++) {
                price[i] = price[i] + 2;
            }
            ((WildCard) card).setPrice(price);
        } else if (card instanceof DoubleWildCard) {
            currentCharacter.hotelColor = ((DoubleWildCard) card).getCurrentColor();
            int[] price = ((DoubleWildCard) card).getCurrentPrice();
            for (int i = 0; i < price.length; i++) {
                price[i] = price[i] + 2;
            }
            ((DoubleWildCard) card).setCurrentPrice(price);
        }
        currentCharacter.hotel = true;
        currentCharacter.removeCard(thisCard);
        return currentCharacter.hotelColor;
    }


    public void reset(){
        characters = new Character[CHARACTER_AMOUNT];
        steal1 = new boolean[CHARACTER_AMOUNT];
        steal3 = new boolean[CHARACTER_AMOUNT];
        for(int i=0;i<CHARACTER_AMOUNT;i++){
            characters[i] = new Character();
            steal1[i] = false;
            steal3[i] = false;
        }
        turn = 0;
        currentCharacter = characters[turn];
        winner = null;
        deck = DeckReader.generateDeck();
        deck.shuffle();
        for(int i=0;i<CHARACTER_AMOUNT;i++){
            for(int j=0;j<INIT_CARD_AMOUNT;j++){
                characters[i].addCard(deck.deal());
            }
        }
    }

    public static void getInfo(){
        for (int i = 0; i < 3; i++) {
            getInfo(i);
        }
    }

    public static void getInfo(int i){
        Character character = GameController.characters[i];
        System.out.print("===player:"+(i+1));
        System.out.print("  money:"+character.getMoney()+",money cards:"+character.getDollars().size()+",detail:"+character.getDollars());
        System.out.println("    hand cards:"+character.getHand().size()+",detail:"+(character.getHand().stream().map(card -> card.getName()).collect(Collectors.toList())));

    }

}
