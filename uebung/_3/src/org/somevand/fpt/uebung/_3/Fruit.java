package org.somevand.fpt.uebung._3;

public abstract class Fruit {
    private final long harvestDate;
    private final double weight;
    private final Color color;
    private final String origin;

    public Fruit(long harvestDate, double weight, Color color, String origin) {
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

    @Override
    public boolean equals(Object obj) {
        return
                obj instanceof Fruit other &&
                harvestDate == other.harvestDate &&
                weight == other.weight &&
                color.equals(other.color) &&
                origin.equals(other.origin);
    }
}
