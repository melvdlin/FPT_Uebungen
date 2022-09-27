package org.somevand.fpt.uebung._4.impl.gui;

import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;
import org.somevand.fpt.uebung._4.data.Ware;
import org.somevand.fpt.uebung._4.exceptions.*;
import org.somevand.fpt.uebung._4.gui.GUI;
import org.somevand.fpt.uebung._4.gui.GuiDisplayableMarket;
import org.somevand.fpt.uebung._4.gui.GuiGameController;
import org.somevand.fpt.uebung._4.impl.data.observable.ObservableGameState;
import org.somevand.fpt.uebung._4.impl.data.observable.ObservableMarket;
import org.somevand.fpt.uebung._4.impl.serde.BeanSerde;
import org.somevand.fpt.uebung._4.impl.serde.Serde;
import org.somevand.fpt.uebung._4.impl.serde.beans.BeanHelper;
import org.somevand.fpt.uebung._4.impl.serde.beans.GameStateBean;
import org.somevand.fpt.uebung._4.impl.serde.records.GameStateData;
import org.somevand.fpt.uebung._4.impl.serde.records.MarketData;
import org.somevand.fpt.uebung._4.impl.serde.records.PlayerData;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GuiGameControllerImpl implements GuiGameController {
    private static final GameStateData defaultStateData;
    static {
        Random random = new Random();
        int minWareCount = 15;
        int maxWareCount = 45;

        Ware timber = new Ware("Timber", 40, 3, false);
        Ware bread = new Ware("Bread", 20, 1, false);
        Ware detergent = new Ware("Detergent", 35, 2, false);
        Ware cellphones = new Ware("Cellphones", 80, 1, false);
        Ware petrol = new Ware("Petrol", 5, 1, true);
        var wares = List.of(timber, bread, detergent, cellphones, petrol);

        MarketData duisburg = new MarketData("Duisburg", Map.of(
                timber, random.nextInt(minWareCount, maxWareCount + 1),
                bread, random.nextInt(minWareCount, maxWareCount + 1),
                detergent, random.nextInt(minWareCount, maxWareCount + 1),
                cellphones, random.nextInt(minWareCount, maxWareCount + 1),
                petrol, random.nextInt(minWareCount, maxWareCount + 1)
        ), Map.of(
                timber, 2,
                bread, 3,
                detergent, 1,
                cellphones, 4,
                petrol, 1
        ));
        MarketData essen = new MarketData("Essen", Map.of(
                timber, random.nextInt(minWareCount, maxWareCount + 1),
                bread, random.nextInt(minWareCount, maxWareCount + 1),
                detergent, random.nextInt(minWareCount, maxWareCount + 1),
                cellphones, random.nextInt(minWareCount, maxWareCount + 1),
                petrol, random.nextInt(minWareCount, maxWareCount + 1)
        ), Map.of(
                timber, 3,
                bread, 1,
                detergent, 2,
                cellphones, 3,
                petrol, 2
        ));
        MarketData oberhausen = new MarketData("Oberhausen", Map.of(
                timber, random.nextInt(minWareCount, maxWareCount + 1),
                bread, random.nextInt(minWareCount, maxWareCount + 1),
                detergent, random.nextInt(minWareCount, maxWareCount + 1),
                cellphones, random.nextInt(minWareCount, maxWareCount + 1),
                petrol, random.nextInt(minWareCount, maxWareCount + 1)
        ), Map.of(
                timber, 1,
                bread, 4,
                detergent, 3,
                cellphones, 3,
                petrol, 1
        ));
        MarketData duesseldorf = new MarketData("Düsseldorf", Map.of(
                timber, random.nextInt(minWareCount, maxWareCount + 1),
                bread, random.nextInt(minWareCount, maxWareCount + 1),
                detergent, random.nextInt(minWareCount, maxWareCount + 1),
                cellphones, random.nextInt(minWareCount, maxWareCount + 1),
                petrol, random.nextInt(minWareCount, maxWareCount + 1)
        ), Map.of(
                timber, 4,
                bread, 2,
                detergent, 2,
                cellphones, 1,
                petrol, 3
        ));
        var markets = List.of(duisburg, essen, oberhausen, duesseldorf);

        var distanceMatrix = Map.of(
                duisburg,       Map.of(duisburg, 0, essen, 2, oberhausen, 1, duesseldorf, 3),
                essen,          Map.of(duisburg, 2, essen, 0, oberhausen, 1, duesseldorf, 4),
                oberhausen,     Map.of(duisburg, 1, essen, 1, oberhausen, 0, duesseldorf, 4),
                duesseldorf,    Map.of(duisburg, 3, essen, 4, oberhausen, 4, duesseldorf, 0)
        );

        var maxCapacity = 30;
        var startingBalance = 200;
        var winBalance = 800;
        var player = new PlayerData(
                wares.stream().collect(Collectors.toMap(Function.identity(), ware -> 0)),
                maxCapacity,
                startingBalance);

        defaultStateData = new GameStateData(wares, markets, player, distanceMatrix, duisburg, winBalance);
    }
    private final SimpleObjectProperty<ObservableGameState> state = new SimpleObjectProperty<>();
    private final Serde<GameStateBean> serde = new BeanSerde<>(GameStateBean.class);
    private GUI gui = null;

    public GuiGameControllerImpl() {
        state.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null)
                oldValue.getPlayer().getBalance().removeListener(this::winListener);
            if (newValue != null)
                newValue.getPlayer().getBalance().addListener(this::winListener);
        });
        state.setValue(new ObservableGameState(defaultStateData));
    }

    @Override
    public void setup(GUI gui) {
        this.gui = gui;
        gui.setState(state.getValue());
        gui.addOnMarketClickedListener(this::onMarketClicked);
        gui.addOnMarketWareClickedListener(this::onMarketWareClicked);
        gui.addOnPlayerWareClickedListener(this::onPlayerWareClicked);
        gui.addOnSaveButtonClickedListener(this::onSaveButtonClicked);
        gui.addOnLoadButtonClickedListener(this::onLoadButtonClicked);
        gui.addOnExitButtonClickedListener(this::onExitButtonClicked);
        gui.show();
    }

    // region listeners

    private Void onSaveButtonClicked(String path) {
        try {
            serde.serialize(BeanHelper.encode(state.getValue().getData()), path);
        } catch (IOException e) {
            gui.showPopup("Warning", "Serialisation failed!", null);
        }
        return null;
    }

    private Void onLoadButtonClicked(String path) {
        try {
            state.setValue(new ObservableGameState(BeanHelper.decode(serde.deserialize(path))));
            gui.setState(state.getValue());
        } catch (IOException | ClassNotFoundException e) {
            gui.showPopup("Warning", "Serialisation failed!", null);
        }
        return null;
    }

    private Void onExitButtonClicked(Void param) {
        gui.exit();
        return null;
    }

    private Void onMarketClicked(GuiDisplayableMarket market) {
        try {
            if (!(market instanceof ObservableMarket to))
                throw new UnknownMarketException(market.getName());
            state.getValue().travel(to);
        } catch (OutOfFuelException e) {
            gui.showPopup("Info", e.getMessage(), null);
        } catch (UnknownMarketException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Void onMarketWareClicked(Ware ware) {
        try {
            var pm = state.getValue().getCurrentMarket().getValue().getPriceMultiplier(ware);
            state.getValue().getPlayer().checkCanBuy(ware, 1, pm);
            state.getValue().getCurrentMarket().getValue().checkCanSell(ware, 1);

            state.getValue().getPlayer().buy(ware, 1, pm);
            state.getValue().getCurrentMarket().getValue().sell(ware, 1);
        } catch (OutOfWareException ignored) {
        } catch (CannotAffordException | OutOfCapacityException e) {
            gui.showPopup("Info", e.getMessage(), null);
        } catch (UnknownWareException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Void onPlayerWareClicked(Ware ware) {
        try {
            var pm = state.getValue().getCurrentMarket().getValue().getPriceMultiplier(ware);
            state.getValue().getPlayer().checkCanSell(ware, 1);
            state.getValue().getCurrentMarket().getValue().checkCanBuy(ware, 1);

            state.getValue().getPlayer().sell(ware, 1, pm);
            state.getValue().getCurrentMarket().getValue().buy(ware, 1);
        } catch (OutOfWareException ignored) {
        } catch (UnknownWareException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private void winListener(Observable observable, Number oldValue, Number newValue) {
        var wb = state.getValue().getWinBalance();
        if (wb > oldValue.intValue() && wb <= newValue.intValue())
            gui.showPopup("Info", "You have made enough money to retire.", null);
    }

    // endregion
}
