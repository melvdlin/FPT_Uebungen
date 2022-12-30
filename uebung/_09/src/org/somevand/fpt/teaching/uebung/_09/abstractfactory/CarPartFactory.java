package org.somevand.fpt.teaching.uebung._09.abstractfactory;

public interface CarPartFactory {
    Engine buildEngine(int numberOfPistons);
    HeadLights buildHeadlights();
    Seat buildSeat(Color color);
}
