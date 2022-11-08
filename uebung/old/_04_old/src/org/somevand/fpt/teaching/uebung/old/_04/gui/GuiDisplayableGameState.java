package org.somevand.fpt.teaching.uebung.old._04.gui;

import javafx.beans.value.ObservableValue;
import org.somevand.fpt.teaching.uebung.old._04.exceptions.UnknownMarketException;

import java.util.List;

public interface GuiDisplayableGameState {
    GuiDisplayablePlayer getPlayer();
    List<? extends GuiDisplayableMarket> getMarkets();
    int getDistance(GuiDisplayableMarket from, GuiDisplayableMarket to) throws UnknownMarketException;
    ObservableValue<? extends GuiDisplayableMarket> getCurrentMarket();
    int getWinBalance();
}
