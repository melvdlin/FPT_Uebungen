package org.somevand.fpt.teaching.uebung._10.producerconsumer;

public class DummyCar {
    private final String model;

    public DummyCar(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "DummyCar{" +
                "model='" + model + '\'' +
                '}';
    }
}
