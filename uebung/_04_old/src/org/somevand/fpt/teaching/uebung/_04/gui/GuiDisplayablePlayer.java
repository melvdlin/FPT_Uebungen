package org.somevand.fpt.teaching.uebung._04.gui;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableMap;
import org.somevand.fpt.teaching.uebung._04.data.Ware;

public interface GuiDisplayablePlayer {
    ObservableMap<Ware, Integer> getInventory();
    ObservableValue<Number> getBalance();
    ObservableValue<Number> getFuelReach();
    int getMaxCapacity();
    ObservableValue<Number> getRemainingCapacity();
}
