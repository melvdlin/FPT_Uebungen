package org.somevand.fpt.uebung._4.gui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import org.somevand.fpt.uebung._4.data.Ware;

public class InventoryEntry {
    private final Ware ware;
    private final StringProperty name;
    private final IntegerProperty price;
    private final IntegerProperty size;
    private final IntegerProperty count;

    protected InventoryEntry(Ware ware, ObservableValue<? extends GuiDisplayableMarket> market) {
        this.ware = ware;
        this.name = new SimpleStringProperty(ware.name());
        this.price = new SimpleIntegerProperty(ware.basePrice());
        this.size = new SimpleIntegerProperty(ware.size());
        this.count = new SimpleIntegerProperty(0);
    }

    public Ware getWare() {
        return ware;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public IntegerProperty sizeProperty() {
        return size;
    }

    public IntegerProperty countProperty() {
        return count;
    }
}
