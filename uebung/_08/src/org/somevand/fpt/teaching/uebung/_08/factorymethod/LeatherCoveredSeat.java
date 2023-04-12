package org.somevand.fpt.teaching.uebung._08.factorymethod;

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
