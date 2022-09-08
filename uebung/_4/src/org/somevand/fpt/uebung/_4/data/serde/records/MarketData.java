package org.somevand.fpt.uebung._4.data.serde.records;

import org.somevand.fpt.uebung._4.data.Ware;

import java.io.Serializable;
import java.util.Map;

public record MarketData(
        String name,
        Map<Ware, Integer> inventory,
        Map<Ware, Integer> priceMultipliers
) implements Serializable {

}
