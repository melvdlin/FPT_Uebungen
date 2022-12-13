package org.somevand.fpt.teaching.uebung._08.factorymethod;

import org.somevand.fpt.teaching.uebung._08.factorymethod.Color;
import org.somevand.fpt.teaching.uebung._08.factorymethod.Seat;

public class LeatherCoveredSeat implements Seat {

    private Color color;

    public LeatherCoveredSeat(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
