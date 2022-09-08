package org.somevand.fpt.uebung._4.data.observable;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import org.somevand.fpt.uebung._4.data.Ware;
import org.somevand.fpt.uebung._4.exceptions.*;
import org.somevand.fpt.uebung._4.gui.GuiDisplayablePlayer;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ObservablePlayer implements GuiDisplayablePlayer, Serializable {

    private final ObservableMap<Ware, Integer> inventory = FXCollections.observableHashMap();
    private int maxCapacity;
    private final SimpleIntegerProperty remainingCapacity = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty balance = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty fuelReach = new SimpleIntegerProperty(0);

    private ObservablePlayer() {
        inventory.addListener((MapChangeListener<? super Ware, ? super Integer>) change -> {
            remainingCapacity.set(calculateRemainingCapacity());
            if (change.getKey().isFuel()) fuelReach.set(calculateFuelReach());
        });
    }

    private ObservablePlayer(int maxCapacity, int balance) {
        this();
        this.maxCapacity = maxCapacity;
        this.balance.set(balance);
    }

    public ObservablePlayer(int maxCapacity, int balance, List<Ware> wares) {
        this(maxCapacity, balance);
        for (var ware : wares) inventory.put(ware, 0);
    }

    public ObservablePlayer(int maxCapacity, int balance, Map<Ware, Integer> inventory) {
        this(maxCapacity, balance);
        this.inventory.putAll(inventory);
    }

    public int getCount(Ware ware) throws UnknownWareException {
        if (!inventory.containsKey(ware))
            throw new UnknownWareException(ware);
        return inventory.get(ware);
    }

    @Override
    public ObservableMap<Ware, Integer> getInventory() {
        return FXCollections.unmodifiableObservableMap(inventory);
    }

    @Override
    public ReadOnlyIntegerProperty getBalance() {
        return balance;
    }

    @Override
    public ReadOnlyIntegerProperty getFuelReach() {
        return fuelReach;
    }

    @Override
    public int getMaxCapacity() {
        return maxCapacity;
    }

    @Override
    public ReadOnlyIntegerProperty getRemainingCapacity() {
        return remainingCapacity;
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
        balance.subtract(ware.basePrice() * count * priceMultiplier);
        inventory.replace(ware, inventory.get(ware) + count);
    }

    public void sell(Ware ware, int count, int priceMultiplier)
            throws OutOfWareException, UnknownWareException, IllegalArgumentException {
        checkCanSell(ware, count);
        balance.add(ware.basePrice() * count * priceMultiplier);
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
        if (getFuelReach().get() < distance) throw new OutOfFuelException(distance, getFuelReach().get());
    }

    public void checkCanBuy(Ware ware, int count, int priceMultiplier)
            throws UnknownWareException, CannotAffordException, OutOfCapacityException, IllegalArgumentException {
        if (count < 0) throw new IllegalArgumentException("count must not be negative");
        if (!inventory.containsKey(ware)) throw new UnknownWareException(ware);
        if (balance.get() < ware.basePrice() * count * priceMultiplier) throw new CannotAffordException(ware, count, priceMultiplier, balance.get());
        if (getRemainingCapacity().get() < ware.size() * count) throw new OutOfCapacityException(ware, count, getRemainingCapacity().get());
    }

    public void checkCanSell (Ware ware, int count)
            throws UnknownWareException, OutOfWareException, IllegalArgumentException {
        if (count < 0) throw new IllegalArgumentException("count must not be negative");
        if (!inventory.containsKey(ware)) throw new UnknownWareException(ware);
        if (inventory.get(ware) < count) throw new OutOfWareException(ware, count, inventory.get(ware));
    }

    private int calculateRemainingCapacity() {
        return inventory
                .entrySet()
                .stream()
                .mapToInt(value -> value.getKey().size() * value.getValue())
                .sum();
    }

    private int calculateFuelReach() {
        return inventory
                .entrySet()
                .stream()
                .filter(value -> value.getKey().isFuel())
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
