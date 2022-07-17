package org.somevand.fpt.uebung._4;

import org.somevand.fpt.uebung._4.exceptions.UnknownWareException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Player {
    private Map<Ware, Integer> inventory;
    private int maxCapacity;
    private int balance;

    public Player(int maxCapacity, int balance, Collection<Ware> wares) {
        this.maxCapacity = maxCapacity;
        this.balance = balance;
        inventory = new HashMap<>();
        for (var ware : wares) inventory.put(ware, 0);
    }

    public int getCount(Ware ware) throws UnknownWareException {
        if (!inventory.containsKey(ware)) throw new UnknownWareException(ware);
        return inventory.get(ware);
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getRemainingCapacity() {
        int capacity = maxCapacity;
        for (var entry : inventory.entrySet())
            capacity -= entry.getKey().size() * entry.getValue();
        return capacity;
    }

    public int getBalance() {
        return balance;
    }

    public int getFuelReach() {
        int reach = 0;
        for (var entry : inventory.entrySet())
            if (entry.getKey().isFuel())
                reach += entry.getValue();
        return reach;
    }
}
