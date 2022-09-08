package org.somevand.fpt.uebung._4.gui;

import javafx.beans.value.ObservableValue;
import org.somevand.fpt.uebung._4.data.Ware;

import java.util.List;

public interface GuidDIsplayableGameState {
    GuiDisplayablePlayer getPlayer();
    List<? extends GuiDisplayableMarket> getMarkets();
    List<Ware> getWares();
    int getDistance(GuiDisplayableMarket from, GuiDisplayableMarket to);
    ObservableValue<? extends GuiDisplayableMarket> getCurrentMarket();
}
