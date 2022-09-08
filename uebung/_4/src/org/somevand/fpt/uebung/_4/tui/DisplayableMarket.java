package org.somevand.fpt.uebung._4.tui;

import org.somevand.fpt.uebung._4.data.Ware;
import org.somevand.fpt.uebung._4.exceptions.UnknownWareException;

public interface DisplayableMarket {
    String getName();
    int getCount(Ware ware) throws UnknownWareException;
    int getLocalPrice(Ware ware) throws UnknownWareException;
}
