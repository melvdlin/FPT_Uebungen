package org.somevand.fpt.teaching.uebung._09.abstractfactory;

public class LeatherCoveredSeat implements Seat {

    private Color color;

    public LeatherCoveredSeat(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "LeatherCoveredSeat{" +
                "color=" + color +
                '}';
    }
}
