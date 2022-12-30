package org.somevand.fpt.teaching.uebung._09.abstractfactory;

public class SuperAwesomePetrolEngine implements Engine {

    private int numberOfPistons;

    public SuperAwesomePetrolEngine(int numberOfPistons) {
        this.numberOfPistons = numberOfPistons;
    }

    @Override
    public int getNumberOfPistons() {
        return numberOfPistons;
    }

    @Override
    public double getSize() {
        return numberOfPistons * 0.8;
    }

    @Override
    public double getFuelConsumption() {
        return Math.pow(1.7, numberOfPistons);
    }

    @Override
    public String toString() {
        return "SuperAwesomePetrolEngine{" +
                "numberOfPistons=" + numberOfPistons +
                '}';
    }
}
