package org.somevand.fpt.teaching.uebung._03.obst;

public class Pear extends AbstractFruit {
    public Pear(long harvestDate, double weight, Color color, String origin) {
        super(harvestDate, weight, color, origin);
    }

    @Override
    public boolean equals(Object obj) {
        return
                this == obj ||
                obj instanceof Pear other &&
                getHarvestDate() == other.getHarvestDate() &&
                getWeight() == other.getWeight() &&
                getColor().equals(other.getColor()) &&
                getOrigin().equals(other.getOrigin());
    }
}
