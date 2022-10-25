package org.somevand.fpt.teaching.uebung._03_old.obst;

public class Watermelon extends AbstractFruit {
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
                this == obj ||
                obj instanceof Watermelon other &&
                getHarvestDate() == other.getHarvestDate() &&
                getWeight() == other.getWeight() &&
                getColor().equals(other.getColor()) &&
                getOrigin().equals(other.getOrigin()) &&
                this.waterContent == other.waterContent;
    }
}
