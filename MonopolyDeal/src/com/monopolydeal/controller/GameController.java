package com.monopolydeal.controller;

import com.monopolydeal.model.CardDeck;
import com.monopolydeal.model.CardSet;
import com.monopolydeal.model.card.Card;
import com.monopolydeal.util.DeckReader;
import com.monopolydeal.view.CardComponent;
import com.monopolydeal.view.GameFrame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameController {
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
    private Character[] characters;
    private boolean[] steal1;
    private boolean[] steal3;
    private Character currentCharacter;
    private Character winner;
    private int turn = 0;



}
