package org.somevand.fpt.uebung._04.exceptions;

import org.somevand.fpt.uebung._04.data.Ware;

public class UnknownWareException extends GameException {
    private final String wareName;

    public UnknownWareException(String wareName) {
        this.wareName = wareName;
    }

    public UnknownWareException(Ware ware) {
        this.wareName = ware.name();
    }

    @Override
    public String getMessage() {
        return "Unknown ware: " + wareName;
    }
}
