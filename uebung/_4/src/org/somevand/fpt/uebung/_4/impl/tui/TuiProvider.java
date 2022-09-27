package org.somevand.fpt.uebung._4.impl.tui;

import org.somevand.fpt.uebung._4.tui.TUI;

public class TuiProvider {
    public static TUI getTui() {
        return new TuiImpl();
    }
}
