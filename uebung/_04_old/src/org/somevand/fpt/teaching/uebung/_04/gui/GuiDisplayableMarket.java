package org.somevand.fpt.teaching.uebung._04.gui;

import javafx.collections.ObservableMap;
import org.somevand.fpt.teaching.uebung._04.data.Ware;
import org.somevand.fpt.teaching.uebung._04.exceptions.UnknownWareException;

public interface GuiDisplayableMarket {
    String getName();
    ObservableMap<Ware, Integer> getInventory();
    int getLocalPrice(Ware ware) throws UnknownWareException;
}
