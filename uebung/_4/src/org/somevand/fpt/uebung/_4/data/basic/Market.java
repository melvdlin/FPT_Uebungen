package org.somevand.fpt.uebung._4.data.basic;

import org.somevand.fpt.uebung._4.data.Ware;
import org.somevand.fpt.uebung._4.exceptions.OutOfWareException;
import org.somevand.fpt.uebung._4.exceptions.UnknownWareException;
import org.somevand.fpt.uebung._4.tui.DisplayableMarket;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Market implements DisplayableMarket, Serializable {
    private final String name;
    private final Map<Ware, Integer> priceMultipliers;
    private final Map<Ware, Integer> inventory;

    public Market(String name, Map<Ware, Integer> priceMultipliers, Map<Ware, Integer> inventory) {
        this.name = name;
        this.priceMultipliers = Map.copyOf(priceMultipliers);
        this.inventory = Map.copyOf(inventory);
    }

    public Market(String name, Map<Ware, Integer> priceMultipliers, int meanWareCount) {
        this.name = name;
        this.priceMultipliers = Map.copyOf(priceMultipliers);
        this.inventory = new HashMap<>();
        Random rand = new Random();
        for (var ware : priceMultipliers.keySet()) {
            inventory.put(ware, (int) (meanWareCount * rand.nextDouble(0.5, 1.5)));
        }
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int getCount(Ware ware)
            throws UnknownWareException {
        if (!inventory.containsKey(ware)) throw new UnknownWareException(ware);
        return inventory.get(ware);
    }

    public int getPriceMultiplier(Ware ware)
        throws UnknownWareException {
        if (!priceMultipliers.containsKey(ware)) throw new UnknownWareException(ware);
        return priceMultipliers.get(ware);
    }

    @Override
    public int getLocalPrice(Ware ware)
            throws UnknownWareException {
        return ware.basePrice() * getPriceMultiplier(ware);
    }

    public void buy(Ware ware, int count)
            throws UnknownWareException, IllegalArgumentException {
        checkCanBuy(ware, count);
        inventory.replace(ware, inventory.get(ware) + count);
    }

    public void sell(Ware ware, int count)
            throws UnknownWareException, OutOfWareException, IllegalArgumentException {
        checkCanSell(ware, count);
        inventory.replace(ware, inventory.get(ware) - count);
    }

    public boolean canBuy(Ware ware, int count) {
        try {
            checkCanBuy(ware, count);
            return true;
        } catch (UnknownWareException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean canSell(Ware ware, int count) {
        try {
            checkCanSell(ware, count);
            return true;
        } catch (UnknownWareException | OutOfWareException | IllegalArgumentException e) {
            return false;
        }
    }

    public void checkCanBuy(Ware ware, int count)
            throws UnknownWareException, IllegalArgumentException {
        if (count < 0) throw new IllegalArgumentException("count must not be negative");
        if (!inventory.containsKey(ware)) throw new UnknownWareException(ware);
    }

    public void checkCanSell(Ware ware, int count)
            throws UnknownWareException, OutOfWareException, IllegalArgumentException {
        if (count < 0) throw new IllegalArgumentException("count must not be negative");
        if (!inventory.containsKey(ware)) throw new UnknownWareException(ware);
        if (inventory.get(ware) < count) throw new OutOfWareException(ware, count, inventory.get(ware));
    }
}
