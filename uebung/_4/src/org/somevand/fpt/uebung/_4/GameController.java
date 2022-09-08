package org.somevand.fpt.uebung._4;

import org.somevand.fpt.uebung._4.data.Ware;
import org.somevand.fpt.uebung._4.data.basic.GameState;
import org.somevand.fpt.uebung._4.data.basic.Market;
import org.somevand.fpt.uebung._4.data.serde.Serde;
import org.somevand.fpt.uebung._4.exceptions.*;
import org.somevand.fpt.uebung._4.tui.TUI;
import org.somevand.fpt.uebung._4.tui.TuiProvider;

import java.io.IOException;
import java.util.Arrays;

public class GameController {
    private static class UIStrings {
        public static final String gameActionPrompt = "You decide to ...";
        public static final String gameActionTravel = "sailto";
        public static final String gameActionBuy = "buy";
        public static final String gameActionSell = "sell";
        public static final String gameActionSave = "save";
        public static final String gameActionLoad = "load";
        public static final String gameActionExit = "exit";
        public static String negativeArgErrorString = "must not be negative";
        public static final String gameActionInvalidInput = "Your options are: "
                + gameActionTravel + " <destination>, "
                + gameActionBuy + " <count> <ware>, "
                + gameActionSell + " <count> <ware>, "
                + gameActionExit + ", "
                + gameActionLoad + " <file path>, "
                + gameActionSave + " <file path>";


        public static String negativeNamedArgErrorString(String argName) {
            return argName + " " + negativeArgErrorString;
        }
    }
    private final Serde<GameState> serde;
    private final TUI tui;
    private GameState state;

    public GameController(Serde<GameState> serde) {
        this.serde = serde;
        this.tui = TuiProvider.getTui();
        this.state = null;
    }

    public GameController(Serde<GameState> serde, GameState state) {
        this(serde);
        this.state = state;
    }

    public boolean advance() {
        var playerInput = tui.getInputln(UIStrings.gameActionPrompt);
        var exiting = processPlayerInput(playerInput);
        if (state.getPlayer().getBalance() >= state.getWinBalance()) {
            tui.println("You retire.");
            return false;
        } else {
            return exiting;
        }
    }

    public void run() {
        if (state == null) throw new IllegalStateException();   // TODO : implement save loading
        tui.setState(state);
        tui.update();
        while (advance());
    }

    private boolean processPlayerInput(String input)
            throws IllegalArgumentException {

        var tokens = Arrays.asList(input.split(" +"));

        try {
            if (tokens.size() == 1 && tokens.get(0).equalsIgnoreCase(UIStrings.gameActionExit)) {
                return false;
            } else if (tokens.size() == 2 && tokens.get(0).equalsIgnoreCase(UIStrings.gameActionLoad)) {
                state = serde.deserialize(tokens.get(1));
                tui.setState(state);
                tui.update();
            } else if (tokens.size() == 2 && tokens.get(0).equalsIgnoreCase(UIStrings.gameActionSave)) {
                serde.serialize(state, tokens.get(1));
            } else if (tokens.size() == 2 && tokens.get(0).equalsIgnoreCase(UIStrings.gameActionTravel)) {

                Market destination = state.getMarketByName(tokens.get(1));
                try {
                    state.getPlayer().travel(state.getDistance(state.getCurrentMarket(), destination));
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException(e);  // only catch player caused errors
                }
                state.setCurrentMarket(destination);

                tui.update();

            } else if (tokens.size() == 3 && tokens.get(0).equalsIgnoreCase(UIStrings.gameActionBuy)) {
                int count = Integer.parseInt(tokens.get(1));
                Ware ware = state.getWareByName(tokens.get(2));
                int pm = state.getCurrentMarket().getPriceMultiplier(ware);

                state.getCurrentMarket().checkCanSell(ware, count);
                state.getPlayer().buy(ware, count, pm);
                state.getCurrentMarket().sell(ware, count);

                tui.update();

            } else if (tokens.size() == 3 && tokens.get(0).equalsIgnoreCase(UIStrings.gameActionSell)) {

                int count = Integer.parseInt(tokens.get(1));
                Ware ware = state.getWareByName(tokens.get(2));
                int pm = state.getCurrentMarket().getPriceMultiplier(ware);

                state.getCurrentMarket().checkCanBuy(ware, count);
                state.getPlayer().sell(ware, count, pm);
                state.getCurrentMarket().buy(ware, count);

                tui.update();

            } else tui.println(UIStrings.gameActionInvalidInput);
        } catch (GameException e) {
            tui.println(e.getMessage());
        } catch (NumberFormatException e) {
            tui.println(UIStrings.gameActionInvalidInput);
        } catch (IllegalArgumentException e) {
            tui.println(UIStrings.negativeNamedArgErrorString("count"));
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            tui.println("loading from / saving to file failed");
        }
        return true;
    }
}
