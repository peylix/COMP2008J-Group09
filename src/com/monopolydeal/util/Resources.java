package com.monopolydeal.util;

import javax.swing.border.LineBorder;
import java.awt.*;

public class Resources {
    public static final double VERSION = 1.0;

    public static final String IMG_BG = "assets/images/background.png";
    public static final String IMG_LOGO = "assets/images/logo.png";
    public static final String IMG_LOGO_SMALL = "assets/images/logo_small.png";
    public static final String IMG_BUTTON = "assets/images/button_img.png";
    public static final String IMG_WOODEN_BUTTON ="assets/images/wooden_button.png";
    public static final String IMG_HIDE_LOGO = "assets/images/hide_logo.png";
    public static final String IMG_WILD_CARD = "assets/images/wildcard.png";

    public static final String FILE_DECK = "assets/base_deck.txt";

    public static final String BTN_EXIT_TEXT = "Exit";
    public static final String BTN_START_TEXT = "Start Game";
    public static final String BTN_RETURN_TEXT = "Return to Menu";
    public static final String BTN_QUIT_TEXT = "Quit";
    public static final String BTN_NEW_GAME_TEXT = "New Game";
    public static final String BTN_NEXT_TURN_TEXT = "Next Turn";

    public static final String TEXT_MONEY_CARD = "Money Card";

    public static final String[] AVATAR_NAME;
    public static final String[] AVATAR_LOGO;
    public static final String[] AVATAR_SMALL_LOGO;

    public static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 24);
    public static final Font INFO_FONT = new Font("Arial", Font.BOLD, 14);
    public static final Font PLAYER_FONT = new Font("Arial", Font.PLAIN, 10);
    public static final Font AVATAR_FONT = new Font("Arial", Font.BOLD, 16);
    public static final Font MONEY_FONT = new Font("Arial", Font.PLAIN, 16);
    public static final Font STATE_FONT = new Font("Arial", Font.PLAIN, 14);
    public static final Font WOODEN_BUTTON_TEXT_FONT = new Font("Arial", Font.BOLD, 14);
    public static final Font WOODEN_BUTTON_TURN_FONT = new Font("Arial", Font.PLAIN, 12);
    public static final Font CARD_NAME_FONT = new Font("Arial", Font.PLAIN, 13);
    public static final Font CARD_TYPE_FONT = new Font("Arial", Font.PLAIN, 11);
    public static final Font CARD_INFO_FONT = new Font("Arial", Font.PLAIN, 12);
    public static final Font CARD_FULL_SET_FONT = new Font("Arial", Font.PLAIN, 10);
    public static final Font RENT_CARD_FONT = new Font("Arial", Font.BOLD, 12);
    public static final Font RENT_INFO_FONT = new Font("Arial", Font.PLAIN, 9);

    public static final Color WOODEN_BUTTON_TURN_COLOR = new Color(84, 45, 31);
    public static final Color MONEY_CARD_COLOR = new Color(210, 235, 255);
    public static final Color ACTION_CARD_COLOR = new Color(255, 220, 220);
    public static final Color CARD_HOVER_COLOR =new Color(15, 15, 15);
    public static final Color SELECTED_COLOR = new Color(211, 175, 55);

    public static final BasicStroke LINE_STROKE = new BasicStroke(2);
    public static final LineBorder SELECTED_BORDER = new LineBorder(SELECTED_COLOR, 3);

    static {
        AVATAR_NAME = new String[6];
        AVATAR_LOGO = new String[6];
        AVATAR_SMALL_LOGO = new String[6];
        AVATAR_NAME[0] = "Swimmer";
        AVATAR_NAME[1] = "Mr.Purple";
        AVATAR_NAME[2] = "PinkHat";
        AVATAR_NAME[3] = "YellowHair";
        AVATAR_NAME[4] = "Student";
        AVATAR_NAME[5] = "Teacher";
        AVATAR_LOGO[0] = "assets/images/swimmer.png";
        AVATAR_LOGO[1] = "assets/images/purple_back.png";
        AVATAR_LOGO[2] = "assets/images/female_yellow_back.png";
        AVATAR_LOGO[3] = "assets/images/male_blue_back.png";
        AVATAR_LOGO[4] = "assets/images/female_with_glasses.png";
        AVATAR_LOGO[5] = "assets/images/middle_age_male.png";
        AVATAR_SMALL_LOGO[0] = "assets/images/swimmer_s.png";
        AVATAR_SMALL_LOGO[1] = "assets/images/purple_back_s.png";
        AVATAR_SMALL_LOGO[2] = "assets/images/female_yellow_back_s.png";
        AVATAR_SMALL_LOGO[3] = "assets/images/male_blue_back_s.png";
        AVATAR_SMALL_LOGO[4] = "assets/images/female_with_glasses_s.png";
        AVATAR_SMALL_LOGO[5] = "assets/images/middle_age_male_s.png";
    }
}
