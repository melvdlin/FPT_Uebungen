package org.somevand.fpt.teaching.uebung._09.abstractfactory;

public class DieselEngine implements Engine {

    private int numberOfPistons;

    public DieselEngine(int numberOfPistons) {
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
        return numberOfPistons * 1.3;
    }

    @Override
    public String toString() {
        return "DieselEngine{" +
                "numberOfPistons=" + numberOfPistons +
                '}';
    }
}
