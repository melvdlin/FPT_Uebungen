package org.somevand.fpt.uebung._4.tui;


import org.somevand.fpt.uebung._4.data.Ware;
import org.somevand.fpt.uebung._4.exceptions.UnknownMarketException;

import java.util.List;

public interface DisplayableGameState {
    DisplayablePlayer getPlayer();
    List<? extends DisplayableMarket> getMarkets();
    List<Ware> getWares();
    int getDistance(DisplayableMarket from, DisplayableMarket to) throws UnknownMarketException;
    DisplayableMarket getCurrentMarket();
}
