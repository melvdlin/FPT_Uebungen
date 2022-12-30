package org.somevand.fpt.teaching.uebung._09.abstractfactory;

public class ClothCoveredSeat implements Seat {

    private Color color;

    public ClothCoveredSeat(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "ClothCoveredSeat{" +
                "color=" + color +
                '}';
    }
}
