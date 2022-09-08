package org.somevand.fpt.uebung._4.tui;

import org.somevand.fpt.uebung._4.data.Ware;
import org.somevand.fpt.uebung._4.exceptions.UnknownWareException;

public interface DisplayablePlayer {
    int getCount(Ware ware) throws UnknownWareException;
    int getBalance();
    int getFuelReach();
    int getMaxCapacity();
    int getRemainingCapacity();
}
