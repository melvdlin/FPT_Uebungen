package org.somevand.fpt.teaching.uebung._03.obst;

public class Watermelon extends AbstractFruit {
    private float waterContent;

    public Watermelon(long harvestDate, double weight, Color color, String origin, float waterContent) {
        super(harvestDate, weight, color, origin);
        this.waterContent = waterContent;
    }

    public float getWaterContent() {
        return waterContent;
    }

    public void setWaterContent(float waterContent) {
        this.waterContent = waterContent;
    }

    @Override
    public boolean equals(Object obj) {
        return
                this == obj ||
                obj instanceof Watermelon other &&
                harvestDate == other.harvestDate &&
                weight == other.weight &&
                color.equals(other.color) &&
                origin.equals(other.origin) &&
                waterContent == other.waterContent;
    }
}
