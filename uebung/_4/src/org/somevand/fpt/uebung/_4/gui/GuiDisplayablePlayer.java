package org.somevand.fpt.uebung._4.gui;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.collections.ObservableMap;
import org.somevand.fpt.uebung._4.data.Ware;

public interface GuiDisplayablePlayer {
    ObservableMap<Ware, Integer> getInventory();
    ReadOnlyIntegerProperty getBalance();
    ReadOnlyIntegerProperty getFuelReach();
    int getMaxCapacity();
    ReadOnlyIntegerProperty getRemainingCapacity();
}
