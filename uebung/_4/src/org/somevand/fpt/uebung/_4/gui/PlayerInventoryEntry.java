package org.somevand.fpt.uebung._4.gui;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.value.ObservableValue;
import org.somevand.fpt.uebung._4.data.Ware;

public class PlayerInventoryEntry extends InventoryEntryBase {
    protected final IntegerBinding count;

    public PlayerInventoryEntry(Ware ware, GuiDisplayableGameState state) {
        super(ware, state);
        count = Bindings.createIntegerBinding(
                () -> state.getPlayer().getInventory().get(ware),
                state.getPlayer().getInventory()
        );
    }

    @Override
    public ObservableValue<Number> getCount() {
        return count;
    }
}
