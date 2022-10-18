package org.somevand.fpt.teaching.uebung._04.impl.serde.beans;

public class WareBean {
    private String name;
    private int basePrice;
    private int size;
    private boolean isFuel;

    public WareBean() {

    }

    public WareBean(String name, int basePrice, int size, boolean isFuel) {
        this.name = name;
        this.basePrice = basePrice;
        this.size = size;
        this.isFuel = isFuel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean getIsFuel() {
        return isFuel;
    }

    public void setIsFuel(boolean isFuel) {
        this.isFuel = isFuel;
    }
}
