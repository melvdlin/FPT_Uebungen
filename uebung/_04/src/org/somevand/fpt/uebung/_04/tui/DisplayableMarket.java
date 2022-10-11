package org.somevand.fpt.uebung._04.tui;

import org.somevand.fpt.uebung._04.data.Ware;
import org.somevand.fpt.uebung._04.exceptions.UnknownWareException;

public interface DisplayableMarket {
    String getName();
    int getCount(Ware ware) throws UnknownWareException;
    int getLocalPrice(Ware ware) throws UnknownWareException;
}
