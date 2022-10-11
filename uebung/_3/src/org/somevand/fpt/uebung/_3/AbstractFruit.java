package org.somevand.fpt.uebung._3;

public abstract class AbstractFruit {
    protected final long harvestDate;
    protected final double weight;
    protected final Color color;
    protected final String origin;

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
