package com.monopolydeal.util;

import com.monopolydeal.model.CardDeck;
import com.monopolydeal.model.card.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class DeckReader {

    public static CardDeck generateDeck() {
        CardDeck deck = new CardDeck();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Resources.FILE_DECK));
            String line = reader.readLine();
            while (line != null) {
                if (!line.startsWith("#")) {
                    String[] params = line.split("\\t");
                    String type = params[1];
                    if (type.equals("property")) {
                        deck.add(parsePropertyCard(params));
                    } else if (type.equals("action")) {
                        deck.add(parseActionCard(params));
                    } else if (type.equals("money")) {
                        deck.add(parseMoneyCard(params));
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deck;
    }

    private static Card parsePropertyCard(String[] params){
        String name = params[0];
        int value = Integer.parseInt(params[2]);
        if(params[3].equals("wild")){
            return new WildCard(name, value);
        }
        String[] c = params[3].split(",");
        Color color = new Color(Integer.parseInt(c[0]),Integer.parseInt(c[1]),
                Integer.parseInt(c[2]));
        return new PropertyCard(name, value, color, params[4], params[5],params[6]);
    }

    private static Card parseActionCard(String[] params){
        String name = params[0];
        int value = Integer.parseInt(params[2]);
        int action = 0;
        if(params[3].equals("rent")){
            String[] primary = params[4].split(",");
            String[] secondary = params[5].split(",");
            Color primaryColor = new Color(Integer.parseInt(primary[0]),Integer.parseInt(primary[1]),
                    Integer.parseInt(primary[2]));
            Color secondaryColor = new Color(Integer.parseInt(secondary[0]),Integer.parseInt(secondary[1]),
                    Integer.parseInt(secondary[2]));
            return new RentCard(name, value, primaryColor, secondaryColor);
        }

        if(params[3].equals("draw")){
            action = ActionCard.DRAW;
        }else if(params[3].equals("steal1")){
            action = ActionCard.STEAL1;
        }else if(params[3].equals("steal3")){
            action = ActionCard.STEAL3;
        }else if(params[3].equals("trade")){
            action = ActionCard.TRADE;
        }else if(params[3].equals("takemoney")){
            action = ActionCard.TAKE_MONEY;
        }else if(params[3].equals("doublerent")){
            action = ActionCard.DOUBLE_RENT;
        }else if(params[3].equals("birthday")){
            action = ActionCard.BIRTHDAY;
        }else if(params[3].equals("no")){
            action = ActionCard.BLOCK;
        }
        return new ActionCard(name, value, action);
    }

    private static MoneyCard parseMoneyCard(String[] params){
        String name = params[0];
        int value = Integer.parseInt(params[2]);
        return new MoneyCard(name, value);
    }

}
