package org.somevand.fpt.uebung._4.data.serde.records;

import org.somevand.fpt.uebung._4.data.Ware;

import java.io.Serializable;
import java.util.Map;

public record PlayerData(
        Map<Ware, Integer> inventory,
        int maxCapacity,
        int balance
) implements Serializable {

}
