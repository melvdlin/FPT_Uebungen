package org.somevand.fpt.uebung._4.data.serde.beans;

import java.io.Serializable;

public class PlayerBean implements Serializable {
    private int[] inventory;
    private int maxCapactiy;
    private int balance;

    public PlayerBean() {

    }

    public PlayerBean(int[] inventory, int maxCapactiy, int balance) {
        this.inventory = inventory;
        this.maxCapactiy = maxCapactiy;
        this.balance = balance;
    }

    public int[] getInventory() {
        return inventory;
    }

    public void setInventory(int[] inventory) {
        this.inventory = inventory;
    }

    public int getMaxCapactiy() {
        return maxCapactiy;
    }

    public void setMaxCapactiy(int maxCapactiy) {
        this.maxCapactiy = maxCapactiy;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
