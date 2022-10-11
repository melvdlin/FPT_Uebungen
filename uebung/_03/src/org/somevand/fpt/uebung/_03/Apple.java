package org.somevand.fpt.uebung._03;

public class Apple extends AbstractFruit {
    private boolean isOrganic;

    public Apple(long harvestDate, double weight, Color color, String origin, boolean isOrganic) {
        super(harvestDate, weight, color, origin);
        this.isOrganic = isOrganic;
    }

    public boolean getIsOrganic() {
        return isOrganic;
    }

    public void setIsOrganic(boolean isOrganic) {
        isOrganic = isOrganic;
    }

    @Override
    public boolean equals(Object obj) {
        return
                this == obj ||
                obj instanceof Apple other &&
                harvestDate == other.harvestDate &&
                weight == other.weight &&
                color.equals(other.color) &&
                origin.equals(other.origin) &&
                this.isOrganic == other.isOrganic;
    }
}
