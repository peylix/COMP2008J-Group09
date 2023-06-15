package com.monopolydeal.view.screen;

import com.monopolydeal.controller.GameController;
import com.monopolydeal.model.CardSet;
import com.monopolydeal.model.card.*;
import com.monopolydeal.util.Resources;
import com.monopolydeal.view.CardComponent;
import com.monopolydeal.view.PropertyComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameScreen extends BaseScreen {
    private ImageIcon stdButton = new ImageIcon(Resources.IMG_STD_BUTTON);
    private ImageIcon quitButton = new ImageIcon(Resources.IMG_QUIT_BUTTON);

    private JLabel title;

    private JLabel[] player;
    private JLabel[] names;
    private JLabel[] images;
    private JLabel[] money;
    private JLabel[] block;
    private JLabel[] doubleRent;
    private JLabel[] houses;
    private JLabel[] hotels;

    private JButton nextTurn;
    private JLabel  nextTurnLabel;
    private JLabel  turnLabel;
    private JButton quit;
    private JLabel  quitLabel;

    private ArrayList<CardComponent> cardComponents;
    private ArrayList<PropertyComponent> propertyComponents;

    private Card currentCard;
    private Card tradeCard1;
    private Card tradeCard2;
    private int tradeCharacter1;
    private int tradeCharacter2;
    private String houseState = "";
    private String hotelState = "";

    private ButtonListener buttonListener;
    private CardListener cardListener;
    private WildListener wildListener;
    private RentListener rentListener;
    private StealListener stealListener;
    private TradeListener tradeListener;

    public GameScreen(GameController controller) {
        super(controller);
        // init
        player = new JLabel[3];
        names = new JLabel[3];
        images = new JLabel[3];
        money = new JLabel[3];
        block = new JLabel[3];
        doubleRent = new JLabel[3];
        houses = new JLabel[3];
        hotels = new JLabel[3];
        cardComponents = new ArrayList<>();
        propertyComponents = new ArrayList<>();
        buttonListener = new ButtonListener();
        cardListener = new CardListener();
        wildListener = new WildListener();
        rentListener = new RentListener();
        stealListener = new StealListener();
        tradeListener = new TradeListener();
        setLayout(null);
        createContent();
    }

    private void createContent(){
        // title
        title = new JLabel();
        title.setIcon(new ImageIcon(Resources.IMG_LOGO_SMALL));
        title.setBounds(387, 20, 255, 60);
        add(title);
        // create characters
        for(int i=0;i<3;i++) {
            createPlayer(i);
        }
        // adjust component position
        adjustPosition();
        // create buttons
        createButtons();
        // create properties
        createProperty();
        // create hand cards
        createHandCards();
    }

    private void createHandCards(){
        if(cardComponents.size()>0){
            for(CardComponent component: cardComponents){
                remove(component);
            }
            cardComponents.clear();
        }
        for(int index = 0; index < player.length; index++){
            ArrayList<Card> hand = controller.getCurrentCharacter().getHand();
            int offsetX = 532 - Math.round(hand.size() * 110)/2;
            for(Card card: hand){
                CardComponent component = new CardComponent(card, offsetX, 565, index);
                if (controller.getSteal1Selected(index)||controller.getSteal3Selected(index)) {
                    component.setBorder(BorderFactory.createLineBorder(Resources.SELECTED_COLOR, 10));
                }
                component.addMouseListener(cardListener);
                cardComponents.add(component);
                add(component);
                offsetX += 110;
            }
        }
    }

    private void createProperty(){
        for(PropertyComponent component:propertyComponents){
            remove(component);
        }
        propertyComponents.clear();
        for(int index=0; index<player.length;index++) {
            ArrayList<CardSet> properties = controller.getCharacter(index).getProperties();
            int offsetX = 532 - Math.round(properties.size() * 110) / 2;
            for (CardSet property : properties) {
                for (int i= property.size() - 1; i>=0; i--) {
                    PropertyComponent component = new PropertyComponent(property.getCard(i),
                            offsetX + i * 5, 70 + 165 * index, index);
                    component.addMouseListener(wildListener);
                    component.addMouseListener(rentListener);
                    component.addMouseListener(stealListener);
                    component.addMouseListener(tradeListener);
                    add(component);
                    propertyComponents.add(component);
                }
                offsetX += 130;
            }
        }
    }

    private void createButtons(){
        // next turn label
        nextTurnLabel = new JLabel();
        nextTurnLabel.setText(Resources.BTN_NEXT_TURN_TEXT);
        nextTurnLabel.setForeground(Color.WHITE);
        nextTurnLabel.setFont(Resources.WOODEN_BUTTON_TEXT_FONT);
        nextTurnLabel.setBounds(56,482,130,20);
        nextTurnLabel.addMouseListener(buttonListener);
        add(nextTurnLabel);
        // turn label
        turnLabel= new JLabel();
        turnLabel.setForeground(Resources.WOODEN_BUTTON_TURN_COLOR);
        turnLabel.setFont(Resources.WOODEN_BUTTON_TURN_FONT);
        turnLabel.setText(controller.getCurrentCharacter().getMoves()+"/3");
        turnLabel.setBounds(80,500,130,20);
        turnLabel.addMouseListener(buttonListener);
        add(turnLabel);
        // next turn
        nextTurn = new JButton(stdButton);
        nextTurn.setBounds(40, 470, 110, 66);
        nextTurn.setBorder(null);
        nextTurn.setContentAreaFilled(false);
        nextTurn.addMouseListener(buttonListener);
        add(nextTurn);
        // quit label
        quitLabel = new JLabel();
        quitLabel.setText(Resources.BTN_QUIT_TEXT);
        quitLabel.setForeground(Color.RED);
        quitLabel.setFont(Resources.WOODEN_BUTTON_TEXT_FONT);
        quitLabel.setBounds(915,490,130,20);
        quitLabel.addMouseListener(buttonListener);
        add(quitLabel);
        // quit
        quit = new JButton(quitButton);
        quit.setBounds(880, 470, 110, 66);
        quit.setBorder(null);
        quit.setContentAreaFilled(false);
        quitLabel.addMouseListener(buttonListener);
        add(quit);
    }

    private void createPlayer(int index){
        // player index
        player[index] = new JLabel();
        player[index].setFont(Resources.PLAYER_FONT);
        player[index].setForeground(Color.BLACK);
        add(player[index]);
        // avatar logo
        images[index] = new JLabel();
        add(images[index]);
        // avatar name
        names[index] = new JLabel();
        names[index].setFont(Resources.AVATAR_FONT);
        names[index].setForeground(Color.WHITE);
        add(names[index]);
        // money
        money[index] = new JLabel();
        money[index].setFont(Resources.MONEY_FONT);
        money[index].setForeground(Color.GREEN);
        add(money[index]);
        // block
        block[index] = new JLabel();
        block[index].setFont(Resources.STATE_FONT);
        add(block[index]);
        // double rent
        doubleRent[index] = new JLabel();
        doubleRent[index].setFont(Resources.STATE_FONT);
        add(doubleRent[index]);
        // house state
        houses[index] = new JLabel();
        houses[index].setFont(Resources.STATE_FONT);
        add(houses[index]);
        // hotel state
        hotels[index] = new JLabel();
        hotels[index].setFont(Resources.STATE_FONT);
        add(hotels[index]);
    }

    private void adjustPosition(){
        // character 0
        player[0].setText("Player One");
        player[0].setBounds(22,10,110,12);
        images[0].setBounds(25,25,50,50);
        names[0].setBounds(80,30, 80, 20);
        money[0].setBounds(80,50, 60, 20);
        block[0].setBounds(170,30, 200, 20);
        doubleRent[0].setBounds(170,50, 200, 20);
        houses[0].setBounds(170,70, 200, 20);
        hotels[0].setBounds(170,90, 200, 20);
        // character 1
        player[1].setText("Player Two");
        player[1].setBounds(947,10,110,12);
        images[1].setBounds(950,25,50,50);
        names[1].setBounds(885 ,30, 80, 20);
        money[1].setBounds(885,50, 60, 20);
        block[1].setBounds(780,30, 200, 20);
        doubleRent[1].setBounds(780,50, 200, 20);
        houses[1].setBounds(780,70, 200, 20);
        hotels[1].setBounds(780,90, 200, 20);
        // character 2
        player[2].setText("Player Three");
        player[2].setBounds(22,340,110,12);
        images[2].setBounds(25,355,50,50);
        names[2].setBounds(80,370, 80, 20);
        money[2].setBounds(80,390, 60, 20);
        block[2].setBounds(170,370, 200, 20);
        doubleRent[2].setBounds(170,390, 200, 20);
        houses[2].setBounds(170,410, 200, 20);
        hotels[2].setBounds(170,430, 200, 20);
    }

    @Override
    public void update() {
        for(int i=0;i<3;i++) {
            images[i].setIcon(new ImageIcon(Resources.AVATAR_SMALL_LOGO[controller.getCharacter(i).getAvatarIndex()]));
            names[i].setText(Resources.AVATAR_NAME[controller.getCharacter(i).getAvatarIndex()]);
            money[i].setText("$"+controller.getCharacter(i).getMoney()+"M");
            if(controller.getCharacter(i).getBlockTurns()>0){
                block[i].setForeground(Color.GREEN);
                block[i].setText("√ JustSayNo("+controller.getCharacter(i).getBlockTurns()+")");
            }else{
                block[i].setForeground(Color.RED);
                block[i].setText("X JustSayNo");
            }
            if(controller.getCharacter(i).isDoubleRent()){
                doubleRent[i].setForeground(Color.GREEN);
                doubleRent[i].setText("√ Double Rent");
            }else{
                doubleRent[i].setForeground(Color.RED);
                doubleRent[i].setText("X Double Rent");
            }
            if (controller.getCharacter(i).isHouseUsed()) {
                houses[i].setForeground(Color.GREEN);
                houses[i].setText("√ House Used: " + houseState);
            } else {
                houses[i].setForeground(Color.RED);
                houses[i].setText("X House Not Used");
            }
            if (controller.getCharacter(i).isHotelUsed()) {
                hotels[i].setForeground(Color.GREEN);
                hotels[i].setText("√ Hotel Used: " + hotelState);
            } else {
                hotels[i].setForeground(Color.RED);
                hotels[i].setText("X Hotel Not Used");
            }
            if(controller.getTurn()==i){
                images[i].setBorder(Resources.SELECTED_BORDER);
            }else{
                images[i].setBorder(null);
            }
        }
        turnLabel.setText(controller.getCurrentCharacter().getMoves()+"/3");
        createHandCards();
        createProperty();
        repaint();
    }

    private void showNotice(String message){
        JOptionPane.showMessageDialog(null,
                message,"Tip",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private class ButtonListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource().equals(quit) || e.getSource().equals(quitLabel)) {
                System.exit(0);
            }
            if (e.getSource().equals(nextTurn) || e.getSource().equals(nextTurnLabel)
                    || e.getSource().equals(turnLabel)) {
                controller.nextTurn();
                update();
            }
        }
    }

    private class CardListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("click cardL");
            currentCard = null;
            if(!controller.couldMove()){
                showNotice("Out of moves. Please click [Next Turn]");
                return;
            }
            if(e.getButton() == MouseEvent.BUTTON1){
                if (e.getSource() instanceof CardComponent) {
                    Card card = ((CardComponent) e.getSource()).getCard();
                    if(card.getType()== Card.MONEY_CARD){
                        controller.depositCard(card);
                        update();
                    }else if(card.getType()== Card.PROPERTY_CARD){
                        controller.addProperty(card);
                        update();
                    }else if(card.getType()== Card.WILD_CARD){
                        currentCard  = card;
                        WildCard wildCard = (WildCard) currentCard;
                        String[] options = {"yellow", "red", "orange", "pink", "light blue", "green", "blue", "brown", "dark blue"};
                        int[] defaultPrice = {2, 3, 6};
                        int selectedOption = JOptionPane.showOptionDialog(null, "Which color would you like it to be", "Wild Card", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                        if (selectedOption == 0) {
                            // Set the color to YELLOW.
                            wildCard.setColor(new Color(254,231,15));
                        } else if (selectedOption == 1) {
                            // Set the color to RED.
                            wildCard.setColor(new Color(238,57,36));
                        } else if (selectedOption == 2) {
                            // Set the color to ORANGE.
                            wildCard.setColor(new Color(247,126,35));
                        } else if (selectedOption == 3) {
                            // Set the color to PINK.
                            wildCard.setColor(new Color(239,56,120));
                        } else if (selectedOption == 4) {
                            // Set the color to LIGHT BLUE.
                            wildCard.setColor(new Color(135,165,215));
                        } else if (selectedOption == 5) {
                            // Set the color to GREEN.
                            wildCard.setColor(new Color(19,165,92));
                        } else if (selectedOption == 6) {
                            // Set the color to BLUE.
                            wildCard.setColor(new Color(0,102,204));
                        } else if (selectedOption == 7) {
                            // Set the color to BROWN.
                            wildCard.setColor(new Color(204,102,0));
                        } else if (selectedOption == 8) {
                            // Set the color to DARK BLUE.
                            wildCard.setColor(new Color(0,76,153));

                        }
                        wildCard.setPrice(defaultPrice);
                        controller.addProperty(card);
                        update();



                    } else if (card.getType() == Card.DOUBLE_WILD_CARD) {
                        currentCard  = card;
                        DoubleWildCard doubleWildCard = (DoubleWildCard) currentCard;
                        String[] options = {"Upward", "Downward"};
                        int selectedOption = JOptionPane.showOptionDialog(null, "Which color would you like it to be", "Double Wild Card", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                        if (selectedOption == 0) {
                            // Option 1 selected
                            doubleWildCard.setCurrentColor(doubleWildCard.getColorUpward());
                            doubleWildCard.setCurrentPrice(doubleWildCard.getPriceUpward());
                        } else if (selectedOption == 1) {
                            // Option 2 selected
                            doubleWildCard.setCurrentColor(doubleWildCard.getColorDownward());
                            doubleWildCard.setCurrentPrice(doubleWildCard.getPriceDownward());
                        }
                        controller.addProperty(card);
                        update();
                    } else if(card.getType()== Card.RENT_CARD){
                        if(controller.getCurrentCharacter().getBlockTurns()>0){
                            showNotice("Blocked to use action card.");
                            return;
                        }
                        currentCard  = card;
                        RentCard rent = (RentCard) currentCard;
                        CardComponent component = (CardComponent)e.getSource();
                        if(component.getColor_primary().equals(rent.getPrimary())||component.getColor_secondary().equals(rent.getSecondary())){
                            controller.rent(currentCard, component.getColor_primary(), component.getColor_secondary());
                            update();
                        }else{
                            currentCard = null;
                        }
                        System.out.println("set rent successful");
                    }else if(card.getType()== Card.ACTION_CARD){
                        if(controller.getCurrentCharacter().getBlockTurns()>0){
                            showNotice("Blocked to use action card.");
                            return;
                        }
                        ActionCard actionCard = (ActionCard) card;
                        if(actionCard.getAction() == ActionCard.DRAW){
                            controller.draw(actionCard);
                            update();
                        }else if(actionCard.getAction() == ActionCard.STEAL1){
                            System.out.println("find steal1");
                            currentCard = actionCard;
                            controller.steal1(currentCard);
                            update();
                        }else if(actionCard.getAction() == ActionCard.STEAL3){
                            System.out.println("find steal3");
                            currentCard = actionCard;
                            controller.steal1(currentCard);
                            update();
                        }else if(actionCard.getAction() == ActionCard.TRADE){
                            currentCard = actionCard;
                            controller.trade(currentCard);
                            update();
                        }else if(actionCard.getAction() == ActionCard.TAKE_MONEY){
                            currentCard = actionCard;
                            controller.takeMoney(currentCard);
                            update();
                        }else if(actionCard.getAction() == ActionCard.DOUBLE_RENT){
                            controller.doubleRent(card);
                            update();
                        }else if(actionCard.getAction() == ActionCard.BIRTHDAY){
                            controller.birthday(card);
                            update();
                        }else if(actionCard.getAction() == ActionCard.BLOCK){
                            controller.block(card);
                            update();
                        } else if (actionCard.getAction() == ActionCard.HOUSE) {
                            ArrayList<CardSet> propertiesList = controller.getCurrentCharacter().getProperties();
                            Color houseColor = null;
//                            Color color1 = propertiesList.get(0).getColor();
//                            Color color2 = propertiesList.get(1).getColor();
//                            Color color3 = propertiesList.get(2).getColor();
                            String[] options = {"First Set", "Second Set", "Third Set"};
                            int selectedOption = JOptionPane.showOptionDialog(null, "While set of properties would you like to apply the house card?", "House Card", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                            if (selectedOption == 0) {
                                houseColor = controller.setHouse(propertiesList.get(0).getCard(0), card);
                                houseState = "First Set";
                            } else if (selectedOption == 1) {
                                houseColor = controller.setHouse(propertiesList.get(1).getCard(0), card);
                                houseState = "Second Set";
                            } else if (selectedOption == 2) {
                                houseColor = controller.setHouse(propertiesList.get(2).getCard(0), card);
                                houseState = "Third Set";
                            }
                            if (houseColor == null) {
                                showNotice("You cannot apply the house card... Do something else!");
                            }
                            update();
                        }else if (actionCard.getAction() == ActionCard.HOTEL) {
                            ArrayList<CardSet> propertiesList = controller.getCurrentCharacter().getProperties();
                            Color houseColor = null;
                            String[] options = {"First Set", "Second Set", "Third Set"};
                            int selectedOption = JOptionPane.showOptionDialog(null, "While set of properties would you like to apply the house card?", "House Card", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                            if (selectedOption == 0) {
                                if (houseState.equals("First Set")) {
                                    houseColor = controller.setHotel(propertiesList.get(0).getCard(0), card);
                                    hotelState = "First Set";
                                }

                            } else if (selectedOption == 1) {
                                if (houseState.equals("Second Set")) {
                                    houseColor = controller.setHotel(propertiesList.get(1).getCard(0), card);
                                    houseState = "Second Set";
                                }
                            } else if (selectedOption == 2) {
                                if (houseState.equals("Third Set")) {
                                    houseColor = controller.setHotel(propertiesList.get(2).getCard(0), card);
                                    houseState = "Third Set";
                                }

                            }
                            if (houseColor == null) {
                                showNotice("You cannot apply the house card... Do something else!");
                            }
                            update();
                        }
                    }
                }

                return;
            }

            if (e.getButton() == MouseEvent.BUTTON3) {
                if (e.getSource() instanceof CardComponent) {
                    Card card = ((CardComponent) e.getSource()).getCard();
                    controller.depositCard(card);
                    update();
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if(e.getSource() instanceof CardComponent){
                ((CardComponent)e.getSource()).setHover(true);
                repaint();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource() instanceof CardComponent){
                ((CardComponent)e.getSource()).setHover(false);
                repaint();
            }
        }
    }

    private class WildListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() instanceof PropertyComponent && currentCard instanceof WildCard) {
                PropertyComponent component = (PropertyComponent) e.getSource();
                Card card = component.getCard();
                if (component.getCharacterIndex() != controller.getTurn()) {
                    currentCard = null;
                    return;
                }
                WildCard wild = (WildCard) currentCard;
                if (card instanceof PropertyCard) {
                    wild.setColor(((PropertyCard) card).getColor());
                    wild.setPrice(((PropertyCard) card).getPrice());
                } else if (card instanceof WildCard) {
                    wild.setColor(((WildCard) card).getColor());
                    wild.setPrice(((WildCard) card).getPrice());
                } else if (card instanceof DoubleWildCard) {
                    wild.setColor(((DoubleWildCard) card).getCurrentColor());
                    wild.setPrice(((DoubleWildCard) card).getCurrentPrice());
                }
                controller.addWildProperty(wild);
                currentCard = null;
                update();
            }
        }
    }

    private class RentListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("click");
            if (e.getSource() instanceof PropertyComponent && currentCard instanceof RentCard) {
                PropertyComponent component = (PropertyComponent)e.getSource();
                if(component.getCharacterIndex()!=controller.getTurn()){
                    currentCard = null;
                    return;
                }
                RentCard rent = (RentCard) currentCard;
//                if(component.getColor().equals(rent.getPrimary())||component.getColor().equals(rent.getSecondary())){
//                    controller.rent(currentCard, component.getColor());
//                    update();
//                }else{
//                    currentCard = null;
//                }
            }
        }
    }

    private class StealListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() instanceof PropertyComponent){
                PropertyComponent component = (PropertyComponent)e.getSource();
                int index = component.getCharacterIndex();
                if(controller.getSteal1Selected(index)){
                    controller.steal1(currentCard);
                    currentCard = null;
                    update();
                }else if(controller.getSteal3Selected(index)){
                    controller.steal3(index, component.getColor(), currentCard);
                    currentCard = null;
                    update();
                }
            }
        }
    }

    private class TradeListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            if(currentCard == null){
                return;
            }
            if(currentCard instanceof ActionCard){
                ActionCard card = (ActionCard) currentCard;
                if(card.getAction()!= ActionCard.TRADE){
                    return;
                }
            }
            if(e.getSource() instanceof PropertyComponent && tradeCard1 == null){
                PropertyComponent component = (PropertyComponent)e.getSource();
                tradeCard1 = component.getCard();
                tradeCharacter1 = component.getCharacterIndex();
                return;
            }
            if(e.getSource() instanceof PropertyComponent && tradeCard1 != null){
                PropertyComponent component = (PropertyComponent)e.getSource();
                tradeCard2 = component.getCard();
                tradeCharacter2 = component.getCharacterIndex();
                if(tradeCharacter1!=controller.getTurn()&&tradeCharacter2==controller.getTurn()){
//                    controller.trade(currentCard, tradeCard2, tradeCard1, tradeCharacter1);
                    update();
                }else if(tradeCharacter1==controller.getTurn()&&tradeCharacter2!=controller.getTurn()){
//                    controller.trade(currentCard, tradeCard1, tradeCard2, tradeCharacter2);
                    update();
                }
                currentCard = null;
                tradeCard1 = null;
                tradeCard2 = null;
            }
        }
    }
}
