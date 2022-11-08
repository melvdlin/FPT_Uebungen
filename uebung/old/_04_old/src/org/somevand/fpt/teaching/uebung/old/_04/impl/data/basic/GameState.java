package org.somevand.fpt.teaching.uebung.old._04.impl.data.basic;

import org.somevand.fpt.teaching.uebung.old._04.tui.DisplayableGameState;
import org.somevand.fpt.teaching.uebung.old._04.tui.DisplayableMarket;
import org.somevand.fpt.teaching.uebung.old._04.data.Ware;
import org.somevand.fpt.teaching.uebung.old._04.exceptions.UnknownMarketException;
import org.somevand.fpt.teaching.uebung.old._04.exceptions.UnknownWareException;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameState implements DisplayableGameState, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<Ware> wares;
    private final Map<String, Ware> waresByName;
    private final List<Market> markets;
    private final Map<String, Market> marketsByName;
    private final Player player;
    private final Map<Market, Map<Market, Integer>> marketDistanceMatrix;
    private Market currentMarket;
    private final int winBalance;

    public GameState(
            List<Ware> wares,
            Map<Market, Map<Market, Integer>> marketDistanceMatrix,
            Player player,
            Market initialMarket,
            int winBalance) {
        // wares //
        // eliminate possible duplicates
        this.wares = List.copyOf(Set.copyOf(wares));

        var tempWaresByName = new HashMap<String, Ware>();
        // check for duplicate names
        for (var ware : this.wares) {
            if (ware.name().contains(" ")
            ) throw new IllegalArgumentException(
                    "ware names must not contain whitespace"
            );
            if (null != tempWaresByName.put(
                    ware.name().toLowerCase(),
                    ware)
            ) throw new IllegalArgumentException(
                    "case insensitive ware names must be unique");
        }
        // take immutable copy
        this.waresByName = Map.copyOf(tempWaresByName);

        // markets //
        // take immutable copy
        this.markets = List.copyOf(marketDistanceMatrix.keySet());

        var tempMarketsByName = new HashMap<String, Market>();
        // check for duplicate names
        for (var market : markets) {
            if (market.getName().contains(" ")
            ) throw new IllegalArgumentException(
                    "market names must not contain whitespace"
            );
            if (null != tempMarketsByName.put(
                    market.getName().toLowerCase(),
                    market)
            ) throw new IllegalArgumentException(
                    "case insensitive market names must be unique");
        }
        // take immutable copy
        this.marketsByName = Map.copyOf(tempMarketsByName);

        var tempMarketDistanceMatrix = new HashMap<Market, Map<Market, Integer>>();
        for (var market : markets) {
            // take immutable copy
            var mapping = Map.copyOf(marketDistanceMatrix.get(market));

            // check for completeness of rows
            if (!mapping.keySet().containsAll(markets))
                throw new IllegalArgumentException(
                        "market distance matrix must map all markets to each other");
            if (mapping.values().stream().anyMatch(distance -> distance < 0))
                throw new IllegalArgumentException(
                        "distance between markets must not be negative"
                );

            tempMarketDistanceMatrix.put(market, mapping);
        }
        // take immutable copy
        this.marketDistanceMatrix = Map.copyOf(tempMarketDistanceMatrix);   // immutable

        // player //
        // ensure player inventory contains all known wares
        try {
            for (var ware : this.wares)
                player.getCount(ware);
        } catch (UnknownWareException e) {
            throw new IllegalArgumentException(
                    "player inventory must contain all known wares");
        }
        this.player = player;

        // initial market //
        // ensure initial market is known //
        if (!this.markets.contains(initialMarket))
            throw new IllegalArgumentException("initial market must be known");
        this.currentMarket = initialMarket;

        if (winBalance < 0)
            throw new IllegalArgumentException("win balance must not be negative");
        this.winBalance = winBalance;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public List<Market> getMarkets() {
        return markets;
    }

    @Override
    public List<Ware> getWares() {
        return wares;
    }

    @Override
    public int getDistance(DisplayableMarket from, DisplayableMarket to)
            throws UnknownMarketException {
        if (!(from instanceof Market) || !marketDistanceMatrix.containsKey(from))
            throw new UnknownMarketException(from.getName());
        if (!(to instanceof Market) || !marketDistanceMatrix.get(from).containsKey(to))
            throw new UnknownMarketException(to.getName());
        return marketDistanceMatrix.get(from).get(to);
    }

    @Override
    public Market getCurrentMarket() {
        return currentMarket;
    }

    public void setCurrentMarket(Market market)
            throws UnknownMarketException {
        if (!markets.contains(currentMarket))
            throw new UnknownMarketException(market.getName());
        this.currentMarket = market;
    }

    public Ware getWareByName(String name)
            throws UnknownWareException {
        Ware ware = waresByName.get(name.toLowerCase());
        if (ware == null)
            throw new UnknownWareException(name);
        return ware;
    }
    public Market getMarketByName(String name)
            throws UnknownMarketException {
        Market market = marketsByName.get(name.toLowerCase());
        if (market == null)
            throw new UnknownMarketException(name);
        return market;
    }

    public int getWinBalance() {
        return winBalance;
    }
}
