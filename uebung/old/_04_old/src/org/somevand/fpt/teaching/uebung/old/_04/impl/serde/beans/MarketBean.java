package org.somevand.fpt.teaching.uebung.old._04.impl.serde.beans;

import java.io.Serializable;

public class MarketBean implements Serializable {
    private String name;
    private int[] inventory;
    private int[] priceMultipliers;

    public MarketBean() {

    }

    public MarketBean(String name, int[] inventory, int[] priceMultipliers) {
        this.name = name;
        this.inventory = inventory;
        this.priceMultipliers = priceMultipliers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getInventory() {
        return inventory;
    }

    public void setInventory(int[] inventory) {
        this.inventory = inventory;
    }

    public void setInventory(int index, int count) {
        this.inventory[index] = count;
    }

    public int[] getPriceMultipliers() {
        return priceMultipliers;
    }

    public void setPriceMultipliers(int[] priceMultipliers) {
        this.priceMultipliers = priceMultipliers;
    }

    public void setPriceMutlipliers(int index, int multiplier) {
        this.priceMultipliers[index] = multiplier;
    }
}
