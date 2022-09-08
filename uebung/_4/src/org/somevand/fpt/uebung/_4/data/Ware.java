package org.somevand.fpt.uebung._4.data;

import java.io.Serializable;

public record Ware(String name, int basePrice, int size, boolean isFuel) implements Serializable {

}
