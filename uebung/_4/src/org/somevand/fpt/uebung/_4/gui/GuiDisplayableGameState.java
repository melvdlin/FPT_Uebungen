package org.somevand.fpt.uebung._4.gui;

import javafx.beans.value.ObservableValue;

import java.util.List;

public interface GuiDisplayableGameState {
    GuiDisplayablePlayer getPlayer();
    List<? extends GuiDisplayableMarket> getMarkets();
    int getDistance(GuiDisplayableMarket from, GuiDisplayableMarket to);
    ObservableValue<? extends GuiDisplayableMarket> getCurrentMarket();
    int getWinBalance();
}
