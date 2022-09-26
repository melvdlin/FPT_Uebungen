package org.somevand.fpt.uebung._4.gui;

import javafx.beans.binding.IntegerBinding;
import javafx.beans.value.ObservableValue;
import org.somevand.fpt.uebung._4.data.Ware;

public class MarketInventoryEntry extends InventoryEntryBase {
    protected final IntegerBinding count;

    public MarketInventoryEntry(Ware ware, GuiDisplayableGameState state) {
        super(ware, state);
        count = new IntegerBinding() {            {
                bind(state.getCurrentMarket(), state.getCurrentMarket().getValue().getInventory());
                state.getCurrentMarket().addListener((observable, oldValue, newValue) -> {
                    super.unbind(state.getCurrentMarket(), oldValue.getInventory());
                    bind(state.getCurrentMarket(), newValue.getInventory());
                });
            }
            @Override
            protected int computeValue() {
                try {
                    return state.getCurrentMarket().getValue().getInventory().get(ware);
                } catch (Exception e) {
                    return 0;
                }
            }
        };
    }

    @Override
    public ObservableValue<Number> getCount() {
        return count;
    }
}
