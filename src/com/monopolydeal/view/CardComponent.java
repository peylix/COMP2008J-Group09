package com.monopolydeal.view;

import com.monopolydeal.model.card.*;
import com.monopolydeal.util.Resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CardComponent extends JLabel implements com.monopolydeal.model.Interface.CardComponent {
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

        if(card.getType() == Card.MONEY_CARD){
            paintMoneyCard((Graphics2D) g);
        }else if(card.getType() == Card.PROPERTY_CARD){
            paintPropertyCard((Graphics2D) g);
        }else if(card.getType() == Card.WILD_CARD){
            paintWildCard((Graphics2D) g);
        }else if(card.getType() == Card.RENT_CARD){
            paintRentCard((Graphics2D) g);
        }else if(card.getType() == Card.ACTION_CARD){
            paintActionCard((Graphics2D) g);
        } else if (card.getType() == Card.DOUBLE_WILD_CARD) {
            paintDoubleWildCard((Graphics2D) g);
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
                if(i == ((PropertyCard) this.card).getPrice().length - 1) {
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
            // name
            g.setStroke(originalStroke);
            g.setFont(Resources.CARD_NAME_FONT);
            FontMetrics fm = g.getFontMetrics();
            g.drawString(this.card.getName(), 52 - fm.stringWidth(this.card.getName())/2, 25);
            // price
            g.setFont(new Font("Dialog", Font.PLAIN, 11));
            g.drawString("This card can be", 17, 70);
            g.drawString("used as properties", 14, 82);
            g.drawString("of any color.", 11, 94);
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
            g.drawString("Charge others", 7, 90);
            g.drawString("rent on properties", 17, 100);
            g.drawString("that match the color", 12, 110);
            g.drawString("of the Rent card.", 18, 120);
            // price
            g.setFont(Resources.CARD_NAME_FONT);
            g.drawString(this.card.getValue() + "M", 5, 15);
            // type
            g.setFont(Resources.CARD_TYPE_FONT);
            g.drawString("rent card", 30, 150);
        }
    }

    private void paintDoubleWildCard(Graphics2D g){
        if(hover){
            // border
            paintHoverBorder(g);
            // info
            g.setColor(Color.WHITE);
            g.setFont(Resources.CARD_INFO_FONT);
            g.drawString("Click this card", 3, 40);
            g.drawString("to add it to", 12, 55);
            g.drawString("your property set", 13, 140);
            g.drawString("Right-click to bank", 37, 155);
        }else{
            // border
            paintBorder(g, Color.WHITE);
            // color upward
            g.setColor(((DoubleWildCard)this.card).getColorUpward());
            g.fillRect(4, 5, 92, 30);
            g.setColor(Color.BLACK);
            g.drawRect(4, 5, 92, 30);
            // name
            g.setStroke(originalStroke);
            g.setFont(Resources.CARD_NAME_FONT);
            FontMetrics fm = g.getFontMetrics();
            g.drawString("WILD", 52 - fm.stringWidth(this.card.getName())/2, 25);

            // price upward
            int temp = 50;
            for(int i = 0; i < ((DoubleWildCard) this.card).getPriceUpward().length; i++) {
                if(i == ((DoubleWildCard) this.card).getPriceUpward().length - 1) {
                    g.setFont(Resources.CARD_FULL_SET_FONT);
                    g.drawString("full set", 32,temp+6);
                }
                g.setFont(Resources.CARD_NAME_FONT);
                g.drawString("........", 32, temp+10);
                g.setFont(Resources.CARD_INFO_FONT);
                g.drawString(((DoubleWildCard) this.card).getPriceUpward()[i] + "M", 67, temp+13);
                temp+= 25;
            }

            // price downward
            int tempDown = 50;
            for (int i = ((DoubleWildCard) this.card).getPriceUpward().length - 1; i >= 0; i--) {
                if (i == 0) {
                    g.setFont(Resources.CARD_FULL_SET_FONT);
                    g.drawString("full set", 32, tempDown + 6);
                }
                g.setFont(Resources.CARD_NAME_FONT);
                g.drawString("........", 32, tempDown + 10);
                g.setFont(Resources.CARD_INFO_FONT);
                g.drawString(((DoubleWildCard) this.card).getPriceUpward()[i] + "M", 11, tempDown + 13);
                tempDown += 25;
            }
            // color downward
            g.setColor(((DoubleWildCard)this.card).getColorDownward());
            g.fillRect(4, temp, 92, 30);
            g.setColor(Color.BLACK);
            g.drawRect(4, temp, 92, 30);
            // type
            g.setFont(Resources.CARD_TYPE_FONT);
            g.drawString("VERY WILD", 30, 150);
        }
    }

    private void paintActionCardInfo(Graphics2D g){
        int action = ((ActionCard)this.card).getAction();
        if(action == ActionCard.DRAW){
            g.drawString("Pick up 2 extra", 8, 40);
            g.drawString("cards from the", 10, 55);
            g.drawString("draw pile.", 5, 70);
        }else if(action == ActionCard.STEAL1){
            g.drawString("Steal a property", 11, 40);
            g.drawString("card from the", 25, 55);
            g.drawString("next player.", 5, 70);
        }else if(action == ActionCard.STEAL3){
            g.drawString("Steal a property", 10, 40);
            g.drawString("card set from the", 13, 55);
            g.drawString("next player.", 5, 70);
        }else if(action == ActionCard.TRADE){
            g.drawString("Swap another player's", 8, 40);
            g.drawString("property card", 10, 55);
            g.drawString("with one from your", 4, 70);
            g.drawString("property collection.", 5, 85);
        }else if(action == ActionCard.TAKE_MONEY){
            g.drawString("Use this card", 10, 25);
            g.drawString("to demand", 5, 40);
            g.drawString("5 millions", 6, 55);
            g.drawString("from one other", 8, 70);
            g.drawString("player", 8, 85);
//            g.drawString("in property value", 5, 100);
        }else if(action == ActionCard.DOUBLE_RENT){
            g.drawString("Play with a", 20, 40);
            g.drawString("standard Rent", 10, 55);
            g.drawString("to double it.", 33, 70);
        }else if(action == ActionCard.BIRTHDAY){
            g.drawString("All players pay", 12, 40);
            g.drawString("you 2 millions", 13, 55);
            g.drawString("Happy Birthday!", 15, 70);
        }else if(action == ActionCard.BLOCK){
            g.drawString("Use this card", 8, 40);
            g.drawString("to avoid being", 8, 55);
            g.drawString("effected by others.", 15, 70);
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
