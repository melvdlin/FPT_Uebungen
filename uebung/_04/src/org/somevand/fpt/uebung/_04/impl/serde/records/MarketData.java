package org.somevand.fpt.uebung._04.impl.serde.records;

import org.somevand.fpt.uebung._04.data.Ware;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

public record MarketData(
        String name,
        Map<Ware, Integer> inventory,
        Map<Ware, Integer> priceMultipliers
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
