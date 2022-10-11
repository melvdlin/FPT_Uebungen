package org.somevand.fpt.uebung._04.impl.serde.beans;

import org.somevand.fpt.uebung._04.data.Ware;
import org.somevand.fpt.uebung._04.impl.serde.records.GameStateData;
import org.somevand.fpt.uebung._04.impl.serde.records.MarketData;
import org.somevand.fpt.uebung._04.impl.serde.records.PlayerData;

import java.util.*;

public class BeanHelper {
    public static GameStateBean encode(GameStateData stateData) {
        final Map<Ware, Integer> wareToIntMap = new HashMap<>();
        final Map<MarketData, Integer> marketDataToIntMap = new HashMap<>();


        WareBean[] aWares = new WareBean[stateData.wares().size()];
        for (int i = 0; i < stateData.wares().size(); i++) {
            var ware = stateData.wares().get(i);
            aWares[i] = new WareBean(ware.name(), ware.basePrice(), ware.size(), ware.isFuel());
            wareToIntMap.put(ware, i);
        }

        MarketBean[] aMarkets = new MarketBean[stateData.markets().size()];
        for (int i = 0; i < stateData.markets().size(); i++) {
            var market = stateData.markets().get(i);
            var marketBean = encodeMarket(market, wareToIntMap);
            aMarkets[i] = marketBean;
            marketDataToIntMap.put(market, i);
        }

        PlayerBean player = encodePlayer(stateData.player(), wareToIntMap);

        int[] aMatrix = encodeMarketDistanceMatrix(
                stateData.marketDistanceMatrix(),
                marketDataToIntMap);

        int currentMarket = marketDataToIntMap.get(stateData.currentMarket());

        int winBalance = stateData.winBalance();

        return new GameStateBean(
                aWares,
                aMarkets,
                player,
                aMatrix,
                currentMarket,
                winBalance
        );
    }

    private static MarketBean encodeMarket(MarketData market, Map<Ware, Integer> wareToIntMap) {

        if (wareToIntMap.size() != market.inventory().size() ||
            wareToIntMap.size() != market.priceMultipliers().size())
            throw new IllegalArgumentException();

        String name = market.name();

        int[] inventory = new int[market.inventory().size()];
        for (var entry : market.inventory().entrySet())
            inventory[wareToIntMap.get(entry.getKey())] = entry.getValue();

        int[] multipliers = new int [market.priceMultipliers().size()];
        for (var entry : market.priceMultipliers().entrySet())
            multipliers[wareToIntMap.get(entry.getKey())] = entry.getValue();

        return new MarketBean(name, inventory, multipliers);
    }

    private static PlayerBean encodePlayer(PlayerData player, Map<Ware, Integer> wareToIntMap) {

        if (wareToIntMap.size() != player.inventory().size())
            throw new IllegalArgumentException();

        int[] inventory = new int[player.inventory().size()];
        for (var entry : player.inventory().entrySet())
            inventory[wareToIntMap.get(entry.getKey())] = entry.getValue();

        int maxCapacity = player.maxCapacity();

        int balance = player.balance();

        return new PlayerBean(
                inventory,
                maxCapacity,
                balance
        );
    }

    private static int[] encodeMarketDistanceMatrix(
            Map<MarketData, Map<MarketData, Integer>> matrix,
            Map<MarketData, Integer> marketToIntMap) {

        int marketCount = marketToIntMap.size();
        if (marketCount != matrix.size())
            throw new IllegalArgumentException();

        int[] aMatrix = new int[marketCount * marketCount];
        for (var entry : matrix.entrySet()) {
            int from = marketToIntMap.get(entry.getKey());
            if (marketCount != entry.getValue().size())
                throw new IllegalArgumentException();

            for (var subEntry : entry.getValue().entrySet()) {
                int to = marketToIntMap.get(subEntry.getKey());
                aMatrix[from * marketCount + to] = subEntry.getValue();
            }
        }

        return aMatrix;
    }

    public static GameStateData decode(GameStateBean stateBean) {

        List<Ware> wares = new ArrayList<>(stateBean.getWares().length);
        for (int i = 0; i < stateBean.getWares().length; i++) {
            var wareBean = stateBean.getWares()[i];
            wares.add(new Ware(wareBean.getName(), wareBean.getBasePrice(), wareBean.getSize(), wareBean.getIsFuel()));
        }

        List<MarketData> markets = new ArrayList<>(stateBean.getMarkets().length);
        for (int i = 0; i < stateBean.getMarkets().length; i++) {
            var market = decodeMarket(stateBean.getMarkets()[i], wares);
            markets.add(market);
        }

        PlayerData player = decodePlayer(stateBean.getPlayer(), wares);

        Map<MarketData, Map<MarketData, Integer>> matrix = decodeMarktDistanceMatrix(
                stateBean.getMarketDistanceMatrix(),
                markets
        );

        MarketData currentMarket = markets.get(stateBean.getCurrentMarket());

        int winBalance = stateBean.getWinBalance();

        return new GameStateData(
                wares,
                markets,
                player,
                matrix,
                currentMarket,
                winBalance
        );
    }

    private static MarketData decodeMarket(MarketBean market, List<Ware> wares) {

        if (wares.size() != market.getInventory().length ||
            wares.size() != market.getPriceMultipliers().length)
            throw new IllegalArgumentException();

        String name = market.getName();

        Map<Ware, Integer> inventory = new HashMap<>();
        for (int i = 0; i < market.getInventory().length; i++)
            inventory.put(wares.get(i), market.getInventory()[i]);

        Map<Ware, Integer> multipliers = new HashMap<>();
        for (int i = 0; i < market.getPriceMultipliers().length; i++) {
            multipliers.put(wares.get(i), market.getPriceMultipliers()[i]);
        }

        return new MarketData(name, inventory, multipliers);
    }

    private static PlayerData decodePlayer(PlayerBean player, List<Ware> wares) {

        if (wares.size() != player.getInventory().length)
            throw new IllegalArgumentException();

        Map<Ware, Integer> inventory = new HashMap<>();
        for (int i = 0; i < player.getInventory().length; i++)
            inventory.put(wares.get(i), player.getInventory()[i]);

        int maxCapacity = player.getMaxCapactiy();

        int balance = player.getBalance();

        return new PlayerData(inventory, maxCapacity, balance);
    }

    private static Map<MarketData, Map<MarketData, Integer>> decodeMarktDistanceMatrix(
            int[] aMatrix,
            List<MarketData> markets) {

        int marketCount = markets.size();
        if (marketCount * marketCount != aMatrix.length)
            throw new IllegalArgumentException();

        Map<MarketData, Map<MarketData, Integer>> matrix = new HashMap<>();
        for (int from = 0; from < marketCount; from++) {
            Map<MarketData, Integer> row = new HashMap<>();
            for (int to = 0; to < marketCount; to++) {
                row.put(markets.get(to), aMatrix[from * marketCount + to]);
            }
            matrix.put(markets.get(from), row);
        }

        return matrix;
    }
}
