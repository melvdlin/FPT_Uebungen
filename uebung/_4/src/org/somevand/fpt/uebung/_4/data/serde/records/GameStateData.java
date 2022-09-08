package org.somevand.fpt.uebung._4.data.serde.records;

import org.somevand.fpt.uebung._4.data.Ware;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public record GameStateData(
        List<Ware> wares,
        List<MarketData> markets,
        PlayerData player,
        Map<MarketData, Map<MarketData, Integer>> marketDistanceMatrix,
        MarketData currentMarket,
        int winBalance
) implements Serializable {

}
