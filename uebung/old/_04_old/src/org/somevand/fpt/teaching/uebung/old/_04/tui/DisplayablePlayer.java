package org.somevand.fpt.teaching.uebung.old._04.tui;

import org.somevand.fpt.teaching.uebung.old._04.data.Ware;
import org.somevand.fpt.teaching.uebung.old._04.exceptions.UnknownWareException;

public interface DisplayablePlayer {
    int getCount(Ware ware) throws UnknownWareException;
    int getBalance();
    int getFuelReach();
    int getMaxCapacity();
    int getRemainingCapacity();
}
