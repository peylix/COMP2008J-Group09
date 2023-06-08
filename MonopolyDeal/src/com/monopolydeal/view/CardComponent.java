package com.monopolydeal.view;

import com.monopolydeal.model.card.ActionCard;
import com.monopolydeal.model.card.Card;
import com.monopolydeal.model.card.PropertyCard;
import com.monopolydeal.model.card.RentCard;
import com.monopolydeal.util.Resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CardComponent extends JLabel implements com.monopolydeal.Interface.CardComponent {
    private Card card;
    private  Color color_primary;
    private Color color_secondary;
    private boolean hover = false;
    private Color originalColor;
    private Font originalFont;
    private Stroke originalStroke;
    private int characterIndex;


    public CardComponent(Card card, int x, int y, int characterIndex) {
        super();
        if(card instanceof RentCard){
            color_primary = ((RentCard) card).getPrimary();
            color_secondary = ((RentCard) card).getSecondary();
        }
        this.card = card;
        this.characterIndex = characterIndex;
        setBounds(x, y, 100, 160);
    }

    public Color getColor_primary(){
        return color_primary;
    }

    public Color getColor_secondary(){
        return color_secondary;
    }

    public int getCharacterIndex(){
        return characterIndex;
    }

    @Override
    public void paint(Graphics g) {
        originalColor = g.getColor();
        originalFont = g.getFont();
        originalStroke = ((Graphics2D)g).getStroke();

        if(card.getType()== Card.MONEY_CARD){
            paintMoneyCard((Graphics2D)g);
        }else if(card.getType()== Card.PROPERTY_CARD){
            paintPropertyCard((Graphics2D)g);
        }else if(card.getType()== Card.WILD_CARD){
            paintWildCard((Graphics2D)g);
        }else if(card.getType()== Card.RENT_CARD){
            paintRentCard((Graphics2D)g);
        }else if(card.getType()== Card.ACTION_CARD){
            paintActionCard((Graphics2D)g);
        }
        g.setColor(originalColor);
        g.setFont(originalFont);
        ((Graphics2D)g).setStroke(originalStroke);

    }

    private void paintBorder(Graphics2D g, Color color) {
        g.setColor(color);
        g.fillRect(0, 0, 100, 160);
        g.setColor(Color.BLACK);
        g.setStroke(Resources.LINE_STROKE);
        g.drawRect(0, 0, 100, 160);
    }

    private void paintHoverBorder(Graphics2D g) {
        g.setColor(Resources.CARD_HOVER_COLOR);
        g.fillRect(0, 0, 100, 160);
        g.setColor(Color.BLACK);
        g.setStroke(Resources.LINE_STROKE);
        g.drawRect(0, 0, 100, 160);
    }

    private void paintMoneyCard(Graphics2D g){
        if(hover){
            // border
            paintHoverBorder(g);
            // info
            g.setColor(Color.WHITE);
            g.setFont(Resources.CARD_INFO_FONT);
            g.drawString("Click this card", 11, 40);
            g.drawString("to add its value", 9, 55);
            g.drawString("to your treasury", 8, 70);
            g.drawString("Right-click to", 13, 140);
            g.drawString("bank", 37, 155);
        }else{
            // border
            paintBorder(g, Resources.MONEY_CARD_COLOR);
            // value
            g.drawString(card.getValue() + "M", 5, 15);
            // name
            g.setFont(Resources.CARD_NAME_FONT);
            FontMetrics fm = g.getFontMetrics();
            g.drawString(card.getName(), 50 - (fm.stringWidth(card.getName()))/2, 75);
            // type
            g.setFont(Resources.CARD_TYPE_FONT);
            g.drawString(Resources.TEXT_MONEY_CARD, 23, 150);
        }
    }

    private void paintPropertyCard(Graphics2D g){
        if(hover){
            // border
            paintHoverBorder(g);
            // info
            g.setColor(Color.WHITE);
            g.setFont(Resources.CARD_INFO_FONT);
            g.drawString("Click this card", 10, 40);
            g.drawString("to add it to", 20, 55);
            g.drawString("your property set", 5, 70);
            g.drawString("Right-click to", 13, 140);
            g.drawString("bank", 37, 155);
        }else{
            // border
            paintBorder(g, Color.WHITE);
            // color
            g.setColor(((PropertyCard)this.card).getColor());
            g.fillRect(4, 5, 92, 30);
            g.setColor(Color.BLACK);
            g.drawRect(4, 5, 92, 30);
            // name
            g.setStroke(originalStroke);
            g.setFont(Resources.CARD_NAME_FONT);
            FontMetrics fm = g.getFontMetrics();
            g.drawString(this.card.getName(), 52 - fm.stringWidth(this.card.getName())/2, 25);
            // price
            int temp = 50;
            for(int i = 0; i < ((PropertyCard) this.card).getPrice().length; i++) {
                g.drawRect(5, temp, 20, 20);
                g.drawString(Integer.toString(i+1), 12, temp+15);
                if(i == ((PropertyCard) this.card).getPrice().length-1 ) {
                    g.setFont(Resources.CARD_FULL_SET_FONT);
                    g.drawString("full set", 32,temp+6);
                }
                g.setFont(Resources.CARD_NAME_FONT);
                g.drawString("........", 32, temp+10);
                g.setFont(Resources.CARD_INFO_FONT);
                g.drawString(((PropertyCard) this.card).getPrice()[i] + "M", 67, temp+13);
                temp+= 25;
            }
            // type
            g.setFont(Resources.CARD_TYPE_FONT);
            g.drawString("worth " + this.card.getValue() + "M", 30, 150);
        }
    }

    private void paintWildCard(Graphics2D g){
        BufferedImage wild = null;
        if(hover){
            // border
            paintHoverBorder(g);
            // info
            g.setColor(Color.WHITE);
            g.setFont(Resources.CARD_INFO_FONT);
            g.drawString("Click this card", 10, 40);
            g.drawString("to add it to", 20, 55);
            g.drawString("your property set", 5, 70);
            g.drawString("Right-click to", 13, 140);
            g.drawString("bank", 37, 155);
        }else{
            try {
                wild = ImageIO.read(new File(Resources.IMG_WILD_CARD));
            } catch(Exception e) {
                e.printStackTrace();
            }
            // border
            paintBorder(g, Color.WHITE);
            // color
            g.drawImage(wild, 3, 5, null);
            /*
            if(((PropertyMyCard) this.card).getColor()== Color.BLACK) {
                g.drawImage(wild, 3, 5, null);
            } else {
                g.setColor(((PropertyMyCard) this.card).getColor());
                g.fillRect(4, 5, 92, 30);
                g.setColor(Color.BLACK);
                g.drawRect(4, 5, 92, 30);
            }*/
            // name
            g.setStroke(originalStroke);
            g.setFont(Resources.CARD_NAME_FONT);
            FontMetrics fm = g.getFontMetrics();
            g.drawString(this.card.getName(), 52 - fm.stringWidth(this.card.getName())/2, 25);
            // price
            g.setFont(new Font("Dialog", Font.PLAIN, 11));
            g.drawString("This card may", 17, 70);
            g.drawString("be used on any", 14, 82);
            g.drawString("set of properties.", 11, 94);
            // type
            g.setFont(Resources.CARD_TYPE_FONT);
            g.drawString("worth " + this.card.getValue() + "M", 30, 150);
        }
    }

    private void paintRentCard(Graphics2D g){
        if(hover){
            // border
            paintHoverBorder(g);
            // info
            g.setColor(Color.WHITE);
            g.setFont(Resources.CARD_INFO_FONT);
            g.drawString("Take money from", 3, 40);
            g.drawString("your opponent", 12, 55);
            g.drawString("Right-click to", 13, 140);
            g.drawString("bank", 37, 155);
        }else{
            // border
            paintBorder(g, Color.WHITE);
            // name
            g.setColor(((RentCard)this.card).getPrimary());
            g.fillRect(20, 20, 60, 27);
            g.setColor(((RentCard)this.card).getSecondary());
            g.fillRect(20, 45, 60, 27);
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(12));
            g.drawOval(16,11, 68, 69);
            g.fillOval(30, 26, 40,40);
            g.setColor(Color.BLACK);
            g.setFont(Resources.RENT_CARD_FONT);
            g.drawString("Rent", 37, 50);
            // info
            g.setFont(Resources.RENT_INFO_FONT);
            g.drawString("Your opponent must", 7, 90);
            g.drawString("pay you rent for", 17, 100);
            g.drawString("properties you own", 12, 110);
            g.drawString("in these colours.", 18, 120);
            // price
            g.setFont(Resources.CARD_NAME_FONT);
            g.drawString(this.card.getValue() + "M", 5, 15);
            // type
            g.setFont(Resources.CARD_TYPE_FONT);
            g.drawString("rent card", 30, 150);
        }
    }

    private void paintActionCardInfo(Graphics2D g){
        int action = ((ActionCard)this.card).getAction();
        if(action == ActionCard.DRAW){
            g.drawString("Draw two cards", 8, 40);
            g.drawString("from deck and", 10, 55);
            g.drawString("add to your hand", 5, 70);
        }else if(action == ActionCard.STEAL1){
            g.drawString("Steal one card", 11, 40);
            g.drawString("from your", 25, 55);
            g.drawString("opponents hand", 5, 70);
        }else if(action == ActionCard.STEAL3){
            g.drawString("Steal one card", 10, 40);
            g.drawString("set from your", 13, 55);
            g.drawString("opponents hand.", 5, 70);
        }else if(action == ActionCard.TRADE){
            g.drawString("Select one card", 8, 40);
            g.drawString("from your hand", 10, 55);
            g.drawString("to swap with your", 4, 70);
            g.drawString("opponents hand", 5, 85);
        }else if(action == ActionCard.TAKE_MONEY){
            g.drawString("Steal $5M from", 10, 25);
            g.drawString("your opponent. If", 5, 40);
            g.drawString("they do not have", 6, 55);
            g.drawString("enough money,", 8, 70);
            g.drawString("take equivalent", 8, 85);
            g.drawString("in property value", 5, 100);
        }else if(action == ActionCard.DOUBLE_RENT){
            g.drawString("Double the", 20, 40);
            g.drawString("payout of tariff", 10, 55);
            g.drawString("cards", 33, 70);
        }else if(action == ActionCard.BIRTHDAY){
            g.drawString("Gain $2M and", 12, 40);
            g.drawString("add one card", 13, 55);
            g.drawString("to your hand", 15, 70);
        }else if(action == ActionCard.BLOCK){
            g.drawString("Block opponent", 8, 40);
            g.drawString("action card use", 8, 55);
            g.drawString("for two turns", 15, 70);
        }
    }

    private void paintActionCard(Graphics2D g){
        if(hover){
            // border
            paintHoverBorder(g);
            // info
            g.setColor(Color.WHITE);
            g.setFont(Resources.CARD_INFO_FONT);
            paintActionCardInfo(g);
            g.drawString("Right-click to", 13, 140);
            g.drawString("bank", 37, 155);
        }else{
            // border
            paintBorder(g, Resources.ACTION_CARD_COLOR);
            // price
            g.drawString(this.card.getValue() + "M", 5, 15);
            // name
            FontMetrics fm = g.getFontMetrics();
            g.setFont(Resources.CARD_NAME_FONT);
            g.drawString(card.getName(), 52 - fm.stringWidth(card.getName())/2, 75);
            // type
            g.setFont(Resources.CARD_TYPE_FONT);
            g.drawString("action card", 23, 150);
        }
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public Card getCard() {
        return card;
    }
}
