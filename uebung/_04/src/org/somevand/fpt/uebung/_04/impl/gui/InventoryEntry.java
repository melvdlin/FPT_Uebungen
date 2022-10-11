package org.somevand.fpt.uebung._04.impl.gui;

import javafx.beans.value.ObservableValue;
import org.somevand.fpt.uebung._04.data.Ware;

public interface InventoryEntry {
    Ware getWare();
    ObservableValue<String> getName();
    ObservableValue<Number> getPrice();
    ObservableValue<Number> getSize();
    ObservableValue<Number> getCount();
}
