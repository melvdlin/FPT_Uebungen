package org.somevand.fpt.teaching.uebung.old._04.impl.data.observable;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import org.somevand.fpt.teaching.uebung.old._04.gui.GuiDisplayableGameState;
import org.somevand.fpt.teaching.uebung.old._04.gui.GuiDisplayableMarket;
import org.somevand.fpt.teaching.uebung.old._04.impl.serde.records.GameStateData;
import org.somevand.fpt.teaching.uebung.old._04.impl.serde.records.MarketData;
import org.somevand.fpt.teaching.uebung.old._04.data.Ware;
import org.somevand.fpt.teaching.uebung.old._04.exceptions.OutOfFuelException;
import org.somevand.fpt.teaching.uebung.old._04.exceptions.UnknownMarketException;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ObservableGameState implements GuiDisplayableGameState, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private ObservablePlayer player;
    private Set<Ware> wares;
    private List<ObservableMarket> markets;
    private Map<ObservableMarket, Map<ObservableMarket, Integer>> marketDistanceMatrix;
    private final SimpleObjectProperty<ObservableMarket> currentMarket = new SimpleObjectProperty<>(null);
    private int winBalance;

    public ObservableGameState(
            Collection<Ware> wares,
            Map<ObservableMarket, Map<ObservableMarket, Integer>> marketDistanceMatrix,
            ObservablePlayer player,
            ObservableMarket initialMarket,
            int winBalance
    ) {
        // eliminate potential duplicate wares
        this.wares = Set.copyOf(wares);

        var markets = marketDistanceMatrix.keySet();
        this.markets = List.copyOf(markets);


        Map<ObservableMarket, Map<ObservableMarket, Integer>> tempMarketDistanceMatrix = new HashMap<>();
        for (var market : markets) {
            // take immutable copy of row
            tempMarketDistanceMatrix.put(market, Map.copyOf(marketDistanceMatrix.get(market)));
        }
        // take immutable copy of matrix
        this.marketDistanceMatrix = Map.copyOf(tempMarketDistanceMatrix);

        this.player = player;
        this.currentMarket.setValue(initialMarket);
        this.winBalance = winBalance;

        validate();
    }

    public ObservableGameState(GameStateData data) {
        initFromData(data);
        validate();
    }

    private void initFromData(GameStateData data) {
        Map<MarketData, ObservableMarket> marketDataMap = data
                .markets()
                .stream()
                .collect(Collectors.toMap(
                        marketData -> marketData,
                        ObservableMarket::new));

        this.marketDistanceMatrix = data
                .marketDistanceMatrix()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        matrixRowEntry -> marketDataMap.get(matrixRowEntry.getKey()),
                        matrixRowEntry -> matrixRowEntry
                                .getValue()
                                .entrySet()
                                .stream()
                                .collect(Collectors.toMap(
                                        matrixCellEntry -> marketDataMap.get(matrixCellEntry.getKey()),
                                        Map.Entry::getValue))));

        this.markets = List.copyOf(data.markets().stream().collect(ArrayList::new, (acc, market) -> acc.add(marketDataMap.get(market)), ArrayList::addAll));
        this.wares = Set.copyOf(data.wares());
        this.player = new ObservablePlayer(data.player());
        this.currentMarket.setValue(marketDataMap.get(data.currentMarket()));
        this.winBalance = data.winBalance();
    }

    public GameStateData getData() {
        Map<ObservableMarket, MarketData> marketMap = markets.stream().collect(Collectors.toMap(Function.identity(), ObservableMarket::getData));
        return new GameStateData(
                List.copyOf(wares),
                List.copyOf(marketMap.values()),
                player.getData(),
                marketDistanceMatrix
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                matrixRowEntry -> marketMap.get(matrixRowEntry.getKey()),
                                matrixRowEntry -> matrixRowEntry
                                        .getValue()
                                        .entrySet()
                                        .stream()
                                        .collect(Collectors.toMap(
                                                        matrixCellEntry -> marketMap.get(matrixCellEntry.getKey()),
                                                        Map.Entry::getValue
                                                )
                                        )
                        )),
                marketMap.get(currentMarket.get()),
                winBalance);
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(getData());
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        initFromData((GameStateData) ois.readObject());
        try {
            validate();
        } catch (Throwable e) {
            throw new IOException(e);
        }
    }

    @Override
    public ObservablePlayer getPlayer() {
        return player;
    }

    @Override
    public List<ObservableMarket> getMarkets() {
        return markets;
    }

    @Override
    public int getDistance(GuiDisplayableMarket from, GuiDisplayableMarket to) throws UnknownMarketException {
        if (!(from instanceof ObservableMarket && markets.contains(from)))
            throw new UnknownMarketException(from.getName());
        if (!(to instanceof ObservableMarket && markets.contains(to)))
            throw new UnknownMarketException(to.getName());
        return marketDistanceMatrix.get(from).get(to);
    }

    @Override
    public ObservableValue<ObservableMarket> getCurrentMarket() {
        return currentMarket;
    }

    @Override
    public int getWinBalance() {
        return winBalance;
    }

    public void travel(ObservableMarket to) throws UnknownMarketException, OutOfFuelException {
        if (!markets.contains(to))
            throw new UnknownMarketException(to.getName());
        player.travel(getDistance(currentMarket.getValue(), to));
        currentMarket.set(to);
    }

    private void validate() throws IllegalStateException {
        // check for duplicate markets
        var marketSet = Set.copyOf(markets);
        if (marketSet.size() != markets.size())
            throw new IllegalStateException("markets must not contain duplicates");

        // check wares against player inventory
        if (!player.getInventory().keySet().containsAll(wares) || !wares.containsAll(player.getInventory().keySet()))
            throw new IllegalStateException("player inventory keyset must match wares");
        if (player.getInventory().values().stream().anyMatch(count -> count < 0))
            throw new IllegalStateException("ware counts in player inventory must not be less than zero");

        // check wares against market inventories
        for (var market : markets) {
            if (!market.getInventory().keySet().containsAll(wares) || !wares.containsAll(market.getInventory().keySet()))
                throw new IllegalStateException("market inventory keysets must match wares");
            if (market.getInventory().values().stream().anyMatch(count -> count < 0))
                throw new IllegalStateException("ware counts in market inventories must not be less than zero");
        }

        // check market distance matrix
        for (var market : marketSet) {
            var row = marketDistanceMatrix.get(market);
            if (!row.keySet().containsAll(marketSet) || !marketSet.containsAll(row.keySet()) || row.containsValue(null))
                throw new IllegalStateException("market distance matrix must map all markets to each other in both directions");
            if (row.values().stream().anyMatch(distance -> distance < 0))
                throw new IllegalStateException("distance between markets must not be less than zero");
        }

        if (player.getFuelReach().getValue().intValue() < 0)
            throw new IllegalStateException("player fuel reach must not be less than zero");
        if (player.getBalance().getValue().intValue() < 0)
            throw new IllegalStateException("player balance must not be less than zero");
        if (player.getRemainingCapacity().getValue().intValue() < 0)
            throw new IllegalStateException("player remaining cargo capacity must not be less than zero");
        if (player.getMaxCapacity() < 0)
            throw new IllegalStateException("player max cargo capacity must not be less than zero");
        if (winBalance < 0)
            throw new IllegalStateException("win balance must not be less than zero");
        if (currentMarket.getValue() == null)
            throw new IllegalStateException("current market must not be null");
    }
}
