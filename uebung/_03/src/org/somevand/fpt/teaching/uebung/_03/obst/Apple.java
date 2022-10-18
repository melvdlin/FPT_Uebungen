package org.somevand.fpt.teaching.uebung._03.obst;

public class Apple extends AbstractFruit {
    private final boolean organic;

    public Apple(long harvestDate, double weight, Color color, String origin, boolean isOrganic) {
        super(harvestDate, weight, color, origin);
        this.organic = isOrganic;
    }

    public boolean isOrganic() {
        return organic;
    }

    @Override
    public boolean equals(Object obj) {
        return
                this == obj ||
                obj instanceof Apple other &&
                getHarvestDate() == other.getHarvestDate() &&
                getWeight() == other.getWeight() &&
                getColor().equals(other.getColor()) &&
                getOrigin().equals(other.getOrigin()) &&
                this.organic == other.organic;
    }
}
