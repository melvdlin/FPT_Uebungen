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
                harvestDate == other.harvestDate &&
                weight == other.weight &&
                color.equals(other.color) &&
                origin.equals(other.origin);
    }
}
