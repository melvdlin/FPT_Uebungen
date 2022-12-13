package org.somevand.fpt.teaching.uebung._08.factorymethod;

import org.somevand.fpt.teaching.uebung._08.factorymethod.Engine;

public class PetrolEngine implements Engine {

    private int numberOfPistons;

    public PetrolEngine(int numberOfPistons) {
        this.numberOfPistons = numberOfPistons;
    }

    @Override
    public int getNumberOfPistons() {
        return numberOfPistons;
    }

    @Override
    public double getSize() {
        return numberOfPistons * 0.4;
    }

    @Override
    public double getFuelConsumption() {
        return Math.pow(numberOfPistons * 0.4, 10);
    }

    @Override
    public String toString() {
        return "PetrolEngine{" +
                "numberOfPistons=" + numberOfPistons +
                '}';
    }
}
