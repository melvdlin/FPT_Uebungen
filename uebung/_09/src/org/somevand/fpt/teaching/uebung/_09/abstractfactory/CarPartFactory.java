package org.somevand.fpt.teaching.uebung._09.abstractfactory;

public interface CarPartFactory {

    Engine buildEngine();

    Seat buildSeat(Color color);
}
