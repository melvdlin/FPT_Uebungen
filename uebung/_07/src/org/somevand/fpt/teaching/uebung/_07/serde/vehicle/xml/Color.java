package org.somevand.fpt.teaching.uebung._07.serde.vehicle.xml;

import java.io.Serial;
import java.io.Serializable;

public enum Color implements Serializable {
    RED, BLACK, BLUE;
    @Serial
    private static final long serialVersionUID = 0L;
}
