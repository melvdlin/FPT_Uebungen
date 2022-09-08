package org.somevand.fpt.uebung._4.data.serde.beans;

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

    public int[] getPriceMultipliers() {
        return priceMultipliers;
    }

    public void setPriceMultipliers(int[] priceMultipliers) {
        this.priceMultipliers = priceMultipliers;
    }
}
