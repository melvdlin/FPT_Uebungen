package org.somevand.fpt.uebung._4.data.observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import org.somevand.fpt.uebung._4.data.Ware;
import org.somevand.fpt.uebung._4.data.serde.records.MarketData;
import org.somevand.fpt.uebung._4.exceptions.OutOfWareException;
import org.somevand.fpt.uebung._4.exceptions.UnknownWareException;
import org.somevand.fpt.uebung._4.gui.GuiDisplayableMarket;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ObservableMarket implements GuiDisplayableMarket, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name = "";
    private final Map<Ware, Integer> priceMultipliers = new HashMap<>();
    private final ObservableMap<Ware, Integer> inventory = FXCollections.observableHashMap();

    public ObservableMarket(String name, Map<Ware, Integer> priceMultipliers) {
        this.name = name;
        this.priceMultipliers.putAll(priceMultipliers);
    }

    public ObservableMarket(String name,
                            Map<Ware, Integer> priceMultipliers,
                            Map<Ware, Integer> inventory) {
        this(name, priceMultipliers);
        this.inventory.putAll(inventory);
    }

    public ObservableMarket(String name,
                            Map<Ware, Integer> priceMultipliers,
                            int meanWareCount) {
        this(name, priceMultipliers);
        Random rand = new Random();
        for (var ware : priceMultipliers.keySet()) {
            inventory.put(ware, (int) (meanWareCount * rand.nextDouble(0.5, 1.5)));
        }
    }

    public ObservableMarket(MarketData data) {
        initFromData(data);
    }

    private void initFromData(MarketData data) {
        this.name = data.name();
        this.inventory.putAll(data.inventory());
        this.priceMultipliers.putAll(data.priceMultipliers());
    }

    public MarketData getData() {
        return new MarketData(name, Map.copyOf(inventory), Map.copyOf(priceMultipliers));
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(getData());
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        initFromData((MarketData) ois.readObject());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ObservableMap<Ware, Integer> getInventory() {
        return FXCollections.unmodifiableObservableMap(inventory);
    }

    public int getPriceMultiplier(Ware ware)
            throws UnknownWareException {
        if (!priceMultipliers.containsKey(ware))
            throw new UnknownWareException(ware);
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
        if (count < 0)
            throw new IllegalArgumentException("count must not be negative");
        if (!inventory.containsKey(ware))
            throw new UnknownWareException(ware);
    }

    public void checkCanSell(Ware ware, int count)
            throws UnknownWareException, OutOfWareException, IllegalArgumentException {
        if (count < 0)
            throw new IllegalArgumentException("count must not be negative");
        if (!inventory.containsKey(ware))
            throw new UnknownWareException(ware);
        if (inventory.get(ware) < count)
            throw new OutOfWareException(ware, count, inventory.get(ware));
    }
}
