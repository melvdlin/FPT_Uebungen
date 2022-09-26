package org.somevand.fpt.uebung._4.gui;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import org.somevand.fpt.uebung._4.data.Ware;

public abstract class InventoryEntryBase implements InventoryEntry {

    protected final Ware ware;
    protected final SimpleStringProperty name;
    protected final IntegerBinding price;
    protected final SimpleIntegerProperty size;

    protected InventoryEntryBase(Ware ware, GuiDisplayableGameState state) {
        this.ware = ware;
        this.name = new SimpleStringProperty(ware.name());
        this.price = Bindings.createIntegerBinding(
                () -> state.getCurrentMarket().getValue().getLocalPrice(ware),
                state.getCurrentMarket()
        );
        this.size = new SimpleIntegerProperty(ware.size());
    }

    @Override
    public Ware getWare() {
        return ware;
    }

    @Override
    public ObservableValue<String> getName() {
        return name;
    }

    @Override
    public ObservableValue<Number> getPrice() {
        return price;
    }

    @Override
    public ObservableValue<Number> getSize() {
        return size;
    }
}
