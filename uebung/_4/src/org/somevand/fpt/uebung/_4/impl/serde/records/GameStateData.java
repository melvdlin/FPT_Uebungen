package org.somevand.fpt.uebung._4.impl.serde.records;

import org.somevand.fpt.uebung._4.data.Ware;

import java.io.Serial;
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
    @Serial
    private static final long serialVersionUID = 1L;
}
