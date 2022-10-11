package org.somevand.fpt.uebung._04.tui;


import org.somevand.fpt.uebung._04.data.Ware;
import org.somevand.fpt.uebung._04.exceptions.UnknownMarketException;

import java.util.List;

public interface DisplayableGameState {
    DisplayablePlayer getPlayer();
    List<? extends DisplayableMarket> getMarkets();
    List<Ware> getWares();
    int getDistance(DisplayableMarket from, DisplayableMarket to) throws UnknownMarketException;
    DisplayableMarket getCurrentMarket();
}
