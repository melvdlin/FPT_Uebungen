package org.somevand.fpt.uebung._4.impl.serde.records;

import org.somevand.fpt.uebung._4.data.Ware;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

public record PlayerData(
        Map<Ware, Integer> inventory,
        int maxCapacity,
        int balance
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
