package org.somevand.fpt.uebung._4;

import org.somevand.fpt.uebung._4.data.base.GameState;
import org.somevand.fpt.uebung._4.tui.TUI;
import org.somevand.fpt.uebung._4.tui.TuiProvider;

public class GameController {
    private final TUI tui;
    private GameState state;

    public GameController() {
        this.tui = TuiProvider.getTui();
        this.state = null;
    }

    public GameController(GameState state) {
        this();
        this.state = state;
    }

    public boolean advance() {
        tui.update();

        return false;
    }

    public void run() {
        if (state == null) throw new IllegalStateException();   // TODO : implement save loading
        tui.setState(state);

        while (advance());
    }
}
