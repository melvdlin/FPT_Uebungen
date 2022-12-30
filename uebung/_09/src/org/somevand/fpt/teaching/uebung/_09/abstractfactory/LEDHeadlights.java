package org.somevand.fpt.teaching.uebung._09.abstractfactory;

public class LEDHeadlights implements HeadLights {

    public LEDHeadlights() {

    }

    @Override
    public int getPowerConsumption() {
        return 20;
    }

    @Override
    public String toString() {
        return "LEDHeadlights{}";
    }
}
