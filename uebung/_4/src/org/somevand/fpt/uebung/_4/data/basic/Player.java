package org.somevand.fpt.uebung._4.data.basic;

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

    public void buy(Ware ware, int count, int priceMultiplier)
            throws CannotAffordException, UnknownWareException, OutOfCapacityException, IllegalArgumentException {
        checkCanBuy(ware, count, priceMultiplier);
        balance -= ware.basePrice() * count * priceMultiplier;
        inventory.replace(ware, inventory.get(ware) + count);
    }

    public void sell(Ware ware, int count, int priceMultiplier)
            throws OutOfWareException, UnknownWareException, IllegalArgumentException {
        checkCanSell(ware, count);
        balance += ware.basePrice() * count * priceMultiplier;
        inventory.replace(ware, inventory.get(ware) - count);
    }

    public boolean canTravel(int distance) {
        try {
            checkCanTravel(distance);
            return true;
        } catch (OutOfFuelException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean canBuy(Ware ware, int count, int priceMultiplier) {
        try {
            checkCanBuy(ware, count, priceMultiplier);
            return true;
        } catch (UnknownWareException | CannotAffordException | OutOfCapacityException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean canSell(Ware ware, int count) {
        try {
            checkCanSell(ware, count);
            return true;
        } catch (OutOfWareException | UnknownWareException | IllegalArgumentException e) {
            return false;
        }
    }

    public void checkCanTravel(int distance)
            throws OutOfFuelException, IllegalArgumentException {
        if (distance < 0) throw new IllegalArgumentException("distance must not be negative");
        if (getFuelReach() < distance) throw new OutOfFuelException(distance, getFuelReach());
    }

    public void checkCanBuy(Ware ware, int count, int priceMultiplier)
            throws UnknownWareException, CannotAffordException, OutOfCapacityException, IllegalArgumentException {
        if (count < 0) throw new IllegalArgumentException("count must not be negative");
        if (!inventory.containsKey(ware)) throw new UnknownWareException(ware);
        if (balance < ware.basePrice() * count * priceMultiplier) throw new CannotAffordException(ware, count, priceMultiplier, balance);
        if (getRemainingCapacity() < ware.size() * count) throw new OutOfCapacityException(ware, count, getRemainingCapacity());
    }

    public void checkCanSell (Ware ware, int count)
            throws UnknownWareException, OutOfWareException, IllegalArgumentException {
        if (count < 0) throw new IllegalArgumentException("count must not be negative");
        if (!inventory.containsKey(ware)) throw new UnknownWareException(ware);
        if (inventory.get(ware) < count) throw new OutOfWareException(ware, count, inventory.get(ware));
    }
}
