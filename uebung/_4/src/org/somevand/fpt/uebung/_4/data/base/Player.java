package org.somevand.fpt.uebung._4.data.base;

import org.somevand.fpt.uebung._4.data.Ware;
import org.somevand.fpt.uebung._4.exceptions.*;
import org.somevand.fpt.uebung._4.tui.DisplayablePlayer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player implements DisplayablePlayer, Serializable {
    private final Map<Ware, Integer> inventory;
    private final int maxCapacity;
    private int balance;

    public Player(int maxCapacity, int balance, List<Ware> wares) {
        this.maxCapacity = maxCapacity;
        this.balance = balance;
        inventory = new HashMap<>();
        for (var ware : wares) inventory.put(ware, 0);
    }

    @Override
    public int getCount(Ware ware) throws UnknownWareException {
        if (!inventory.containsKey(ware)) throw new UnknownWareException(ware);
        return inventory.get(ware);
    }

    @Override
    public int getMaxCapacity() {
        return maxCapacity;
    }

    @Override
    public int getRemainingCapacity() {
        int capacity = maxCapacity;
        for (var entry : inventory.entrySet())
            capacity -= entry.getKey().size() * entry.getValue();
        return capacity;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public int getFuelReach() {
        int reach = 0;
        for (var entry : inventory.entrySet())
            if (entry.getKey().isFuel())
                reach += entry.getValue();
        return reach;
    }

    public void travel(int distance)
            throws OutOfFuelException {
        checkCanTravel(distance);
        for (var entry : inventory.entrySet()) {
            if (entry.getKey().isFuel()) {
                entry.setValue(entry.getValue() - distance);
                break;
            }
        }
    }

    private void buy(Ware ware, int count, int priceMultiplier)
            throws CannotAffordException, UnknownWareException, OutOfCapacityException {
        checkCanBuy(ware, count, priceMultiplier);
        balance -= ware.basePrice() * count * priceMultiplier;
        inventory.replace(ware, inventory.get(ware) + count);
    }

    private void sell(Ware ware, int count, int priceMultiplier)
            throws OutOfWareException, UnknownWareException {
        checkCanSell(ware, count);
        balance += ware.basePrice() * count * priceMultiplier;
        inventory.replace(ware, inventory.get(ware) - count);
    }

    private void checkCanTravel(int distance)
            throws OutOfFuelException {
        if (getFuelReach() < distance) throw new OutOfFuelException(distance, getFuelReach());
    }

    private void checkCanBuy(Ware ware, int count, int priceMultiplier)
            throws UnknownWareException, CannotAffordException, OutOfCapacityException {
        if (!inventory.containsKey(ware)) throw new UnknownWareException(ware);
        if (balance < ware.basePrice() * count * priceMultiplier) throw new CannotAffordException(ware, count, priceMultiplier, balance);
        if (getRemainingCapacity() < ware.size() * count) throw new OutOfCapacityException(ware, count, getRemainingCapacity());
    }

    private void checkCanSell (Ware ware, int count)
            throws UnknownWareException, OutOfWareException {
        if (!inventory.containsKey(ware)) throw new UnknownWareException(ware);
        if (inventory.get(ware) < count) throw new OutOfWareException(ware, count, inventory.get(ware));
    }
}
