package org.somevand.fpt.uebung._4.gui;

import javafx.collections.ObservableMap;
import org.somevand.fpt.uebung._4.data.Ware;
import org.somevand.fpt.uebung._4.exceptions.UnknownWareException;

public interface GuiDisplayableMarket {
    String getName();
    ObservableMap<Ware, Integer> getInventory();
    int getLocalPrice(Ware ware) throws UnknownWareException;
}
