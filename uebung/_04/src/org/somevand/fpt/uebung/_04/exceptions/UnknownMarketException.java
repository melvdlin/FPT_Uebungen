package org.somevand.fpt.uebung._04.exceptions;

public class UnknownMarketException extends GameException {
    private final String marketName;

    public UnknownMarketException(String marketName) {
        this.marketName = marketName;
    }

    @Override
    public String getMessage() {
        return "Unknown market: " + marketName;
    }
}
