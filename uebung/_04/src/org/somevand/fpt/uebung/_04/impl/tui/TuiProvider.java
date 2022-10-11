package org.somevand.fpt.uebung._04.impl.tui;

import org.somevand.fpt.uebung._04.tui.TUI;

public class TuiProvider {
    public static TUI getTui() {
        return new TuiImpl();
    }
}
