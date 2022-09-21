package org.somevand.fpt.uebung._4;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import org.somevand.fpt.uebung._4.data.Ware;
import org.somevand.fpt.uebung._4.exceptions.UnknownWareException;
import org.somevand.fpt.uebung._4.gui.GuiDisplayableGameState;
import org.somevand.fpt.uebung._4.gui.GuiDisplayableMarket;
import org.somevand.fpt.uebung._4.gui.GuiDisplayablePlayer;
import org.somevand.fpt.uebung._4.gui.GuiImpl;
import org.somevand.fpt.uebung._4.tui.DisplayableGameState;

import java.util.ArrayList;
import java.util.List;

public class MainGUI {

    public static Ware pen = new Ware("pen", 2, 1, false);
    public static Ware paper = new Ware("paper", 4, 2, true);
    public static ObservableMap<Ware, Integer> mockPlayerIventory = FXCollections.observableHashMap();
    public static ObservableMap<Ware, Integer> mockMarket1Iventory = FXCollections.observableHashMap();
    public static ObservableMap<Ware, Integer> mockMarket2Iventory = FXCollections.observableHashMap();
    public static GuiDisplayablePlayer mockPlayer = new GuiDisplayablePlayer() {
        private final SimpleIntegerProperty balance =  new SimpleIntegerProperty(0);
        private final SimpleIntegerProperty fuel =  new SimpleIntegerProperty(0);
        private final SimpleIntegerProperty capacity =  new SimpleIntegerProperty(0);
        private final int maxCapacity = 20;
        @Override
        public ObservableMap<Ware, Integer> getInventory() {
            return mockPlayerIventory;
        }

        @Override
        public ReadOnlyIntegerProperty getBalance() {
            return balance;
        }

        @Override
        public ReadOnlyIntegerProperty getFuelReach() {
            return fuel;
        }

        @Override
        public int getMaxCapacity() {
            return maxCapacity;
        }

        @Override
        public ReadOnlyIntegerProperty getRemainingCapacity() {
            return capacity;
        }
    };
    public static GuiDisplayableMarket mockMarket1 = new GuiDisplayableMarket() {
        @Override
        public String getName() {
            return "mockMarket1";
        }

        @Override
        public ObservableMap<Ware, Integer> getInventory() {
            return mockMarket1Iventory;
        }

        @Override
        public int getLocalPrice(Ware ware) throws UnknownWareException {
            return ware.basePrice();
        }
    };
    public static GuiDisplayableMarket mockMarket2 = new GuiDisplayableMarket() {
        @Override
        public String getName() {
            return "mockMarket2";
        }

        @Override
        public ObservableMap<Ware, Integer> getInventory() {
            return mockMarket2Iventory;
        }

        @Override
        public int getLocalPrice(Ware ware) throws UnknownWareException {
            return ware.basePrice() * 2;
        }
    };
    public static List<GuiDisplayableMarket> mockMarkets = new ArrayList<>();
    public static final SimpleObjectProperty<GuiDisplayableMarket> mockStateCurrentMarket = new SimpleObjectProperty<>(mockMarket1);
    public static GuiDisplayableGameState mockState = new GuiDisplayableGameState() {
        @Override
        public GuiDisplayablePlayer getPlayer() {
            return mockPlayer;
        }

        @Override
        public List<? extends GuiDisplayableMarket> getMarkets() {
            return mockMarkets;
        }

        @Override
        public int getDistance(GuiDisplayableMarket from, GuiDisplayableMarket to) {
            return 0;
        }

        @Override
        public SimpleObjectProperty<? extends GuiDisplayableMarket> getCurrentMarket() {
            return mockStateCurrentMarket;
        }
    };

    public static void main(String[] args) {
        mockPlayerIventory.put(pen, 1);
        mockPlayerIventory.put(paper, 2);
        mockMarket1Iventory.put(pen, 3);
        mockMarket1Iventory.put(paper, 4);
        mockMarket2Iventory.put(pen, 5);
        mockMarket2Iventory.put(paper, 6);
        mockMarkets.add(mockMarket1);
        mockMarkets.add(mockMarket2);

        GuiImpl.launch(mockState);

        // SimpleObjectProperty<Object> prop = new SimpleObjectProperty<>();
        // prop.addListener(MainGUI::test);
        // prop.set(new Object());
    }

    public static void test(ObservableValue<?> observable, Object oldValue, Object newValue) {
        System.out.println("deez");
    }
}
