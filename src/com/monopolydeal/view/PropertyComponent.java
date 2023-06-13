package com.monopolydeal.view;

import com.monopolydeal.model.card.Card;
import com.monopolydeal.model.card.DoubleWildCard;
import com.monopolydeal.model.card.PropertyCard;
import com.monopolydeal.model.card.WildCard;
import com.monopolydeal.util.Resources;

import javax.swing.*;
import java.awt.*;

public class PropertyComponent extends JLabel {
    private Card card;
    private Color color;
    private Color originalColor;
    private Font originalFont;
    private Stroke originalStroke;
    private int characterIndex;


    public PropertyComponent(Card card, int x, int y, int characterIndex) {
        if(card instanceof PropertyCard){
            color = ((PropertyCard) card).getColor();
        }else if(card instanceof WildCard){
            color = ((WildCard) card).getColor();
        } else if (card instanceof DoubleWildCard) {
            color = ((DoubleWildCard) card).getCurrentColor();
        }
        this.card = card;
        this.characterIndex = characterIndex;
        setBounds(x, y, 100, 160);
    }

    public Card getCard() {
        return card;
    }

    public int getCharacterIndex() {
        return characterIndex;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void paint(Graphics g) {
        originalColor = g.getColor();
        originalFont = g.getFont();
        originalStroke = ((Graphics2D)g).getStroke();
        // border
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 100, 160);
        g.setColor(Color.BLACK);
        ((Graphics2D)g).setStroke(Resources.LINE_STROKE);
        g.drawRect(0, 0, 100, 160);
        // color
        g.setColor(color);
        g.fillRect(4, 5, 92, 30);
        g.setColor(Color.BLACK);
        g.drawRect(4, 5, 92, 30);
        // name
        ((Graphics2D)g).setStroke(originalStroke);
        g.setFont(Resources.CARD_NAME_FONT);
        FontMetrics fm = g.getFontMetrics();
        g.drawString(this.card.getName(), 52 - fm.stringWidth(this.card.getName())/2, 25);
        // price
        int temp = 50;
        int price[] = null;
        if(card instanceof PropertyCard){
            price = ((PropertyCard) card).getPrice();
        }else if(card instanceof WildCard){
            price = ((WildCard) card).getPrice();
        } else if (card instanceof DoubleWildCard) {
            price = ((DoubleWildCard) card).getCurrentPrice();
        }
        for(int i = 0; i < price.length; i++) {
            g.drawRect(5, temp, 20, 20);
            g.drawString(Integer.toString(i+1), 12, temp+15);
            if(i ==  price.length-1 ) {
                g.setFont(Resources.CARD_FULL_SET_FONT);
                g.drawString("full set", 32,temp+6);
            }
            g.setFont(Resources.CARD_NAME_FONT);
            g.drawString("........", 32, temp+10);
            g.setFont(Resources.CARD_INFO_FONT);
            g.drawString(price[i] + "M", 67, temp+13);
            temp+= 25;
        }
        // type
        g.setFont(Resources.CARD_TYPE_FONT);
        g.drawString("worth " + this.card.getValue() + "M", 30, 150);
        g.setColor(originalColor);
        g.setFont(originalFont);
        ((Graphics2D)g).setStroke(originalStroke);
    }
}
