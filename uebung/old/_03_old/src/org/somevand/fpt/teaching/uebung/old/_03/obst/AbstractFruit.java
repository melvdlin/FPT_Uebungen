package org.somevand.fpt.teaching.uebung.old._03.obst;

public abstract class AbstractFruit {
    private final long harvestDate;
    private final double weight;
    private final Color color;
    private final String origin;

    public AbstractFruit(long harvestDate, double weight, Color color, String origin) {
        this.harvestDate = harvestDate;
        this.weight = weight;
        this.color = color;
        this.origin = origin;
    }

    public long getHarvestDate() {
        return harvestDate;
    }

    public double getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }

    public String getOrigin() {
        return origin;
    }
}
