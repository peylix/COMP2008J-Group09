package com.monopolydeal.Interface;

import java.awt.*;

public interface CardComponent {
    Color getColor_primary();
    Color getColor_secondary();
    void setHover(boolean hover);
    Card getCard();
}
