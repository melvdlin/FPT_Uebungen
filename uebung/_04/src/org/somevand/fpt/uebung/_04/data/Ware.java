package org.somevand.fpt.uebung._04.data;

import java.io.Serial;
import java.io.Serializable;

public record Ware(String name, int basePrice, int size, boolean isFuel) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
