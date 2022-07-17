package org.somevand.fpt.uebung._4.exceptions;

import org.somevand.fpt.uebung._4.Market;

public class UnknownMarketException extends GameException {
    private final String marketName;

    public UnknownMarketException(String marketName) {
        this.marketName = marketName;
    }

    public UnknownMarketException(Market market) {
        this.marketName = market.getName();
    }

    @Override
    public String getMessage() {
        return "Unknown market: " + marketName;
    }
}
