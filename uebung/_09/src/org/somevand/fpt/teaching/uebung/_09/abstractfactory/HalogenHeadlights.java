package org.somevand.fpt.teaching.uebung._09.abstractfactory;

public class HalogenHeadlights implements HeadLights {

    public HalogenHeadlights() {

    }

    @Override
    public int getPowerConsumption() {
        return 200;
    }

    @Override
    public String toString() {
        return "HalogenHeadlights{}";
    }
}
