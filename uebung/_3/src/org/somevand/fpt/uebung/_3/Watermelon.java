package org.somevand.fpt.uebung._3;

public class Watermelon extends Fruit {
    private final float waterContent;

    public Watermelon(long harvestDate, double weight, Color color, String origin, float waterContent) {
        super(harvestDate, weight, color, origin);
        this.waterContent = waterContent;
    }

    public float getWaterContent() {
        return waterContent;
    }

    @Override
    public boolean equals(Object obj) {
        return
                super.equals(obj) &&
                obj instanceof Watermelon other &&
                waterContent == other.waterContent;
    }
}
