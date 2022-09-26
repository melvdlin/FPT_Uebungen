package org.somevand.fpt.uebung._4;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import org.somevand.fpt.uebung._4.data.Ware;
import org.somevand.fpt.uebung._4.exceptions.UnknownWareException;
import org.somevand.fpt.uebung._4.gui.GuiDisplayableGameState;
import org.somevand.fpt.uebung._4.gui.GuiDisplayableMarket;
import org.somevand.fpt.uebung._4.gui.GuiDisplayablePlayer;
import org.somevand.fpt.uebung._4.gui.GuiImpl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;

public class MainGUI {

    public static Ware pen = new Ware("pen", 2, 1, false);
    public static Ware paper = new Ware("paper", 4, 2, true);
    public static ObservableMap<Ware, Integer> mockPlayer1Iventory = FXCollections.observableHashMap();
    public static ObservableMap<Ware, Integer> mockMarket1Inventory = FXCollections.observableHashMap();
    public static ObservableMap<Ware, Integer> mockMarket2Inventory = FXCollections.observableHashMap();
    public static GuiDisplayablePlayer mockPlayer1 = new GuiDisplayablePlayer() {
        private final SimpleIntegerProperty balance =  new SimpleIntegerProperty(0);
        private final SimpleIntegerProperty fuel =  new SimpleIntegerProperty(0);
        private final SimpleIntegerProperty capacity =  new SimpleIntegerProperty(0);
        private final int maxCapacity = 20;
        @Override
        public ObservableMap<Ware, Integer> getInventory() {
            return mockPlayer1Iventory;
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
            return mockMarket1Inventory;
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
            return mockMarket2Inventory;
        }

        @Override
        public int getLocalPrice(Ware ware) throws UnknownWareException {
            return ware.basePrice() * 2;
        }
    };
    public static List<GuiDisplayableMarket> mockMarkets1 = new ArrayList<>();
    public static final SimpleObjectProperty<GuiDisplayableMarket> mockState1CurrentMarket = new SimpleObjectProperty<>(mockMarket1);
    public static GuiDisplayableGameState mockState1 = new GuiDisplayableGameState() {
        @Override
        public GuiDisplayablePlayer getPlayer() {
            return mockPlayer1;
        }

        @Override
        public List<? extends GuiDisplayableMarket> getMarkets() {
            return mockMarkets1;
        }

        @Override
        public int getDistance(GuiDisplayableMarket from, GuiDisplayableMarket to) {
            return 5;
        }

        @Override
        public SimpleObjectProperty<? extends GuiDisplayableMarket> getCurrentMarket() {
            return mockState1CurrentMarket;
        }

        @Override
        public int getWinBalance() {
            return 0;
        }
    };
    static {
        mockPlayer1Iventory.put(pen, 1);
        mockPlayer1Iventory.put(paper, 2);
        mockMarket1Inventory.put(pen, 3);
        mockMarket1Inventory.put(paper, 4);
        mockMarket2Inventory.put(pen, 5);
        mockMarket2Inventory.put(paper, 6);
        mockMarkets1.add(mockMarket1);
        mockMarkets1.add(mockMarket2);
    }

    public static Ware brick = new Ware("brick", 9, 3, false);
    public static Ware mortar = new Ware("mortar", 10, 4, true);
    public static ObservableMap<Ware, Integer> mockPlayer2Iventory = FXCollections.observableHashMap();
    public static ObservableMap<Ware, Integer> mockMarket3Inventory = FXCollections.observableHashMap();
    public static ObservableMap<Ware, Integer> mockMarket4Iventory = FXCollections.observableHashMap();
    public static GuiDisplayablePlayer mockPlayer2 = new GuiDisplayablePlayer() {
        private final SimpleIntegerProperty balance =  new SimpleIntegerProperty(10);
        private final SimpleIntegerProperty fuel =  new SimpleIntegerProperty(20);
        private final SimpleIntegerProperty capacity =  new SimpleIntegerProperty(30);
        private final int maxCapacity = 40;
        @Override
        public ObservableMap<Ware, Integer> getInventory() {
            return mockPlayer2Iventory;
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
    public static GuiDisplayableMarket mockMarket3 = new GuiDisplayableMarket() {
        @Override
        public String getName() {
            return "mockMarket3";
        }

        @Override
        public ObservableMap<Ware, Integer> getInventory() {
            return mockMarket3Inventory;
        }

        @Override
        public int getLocalPrice(Ware ware) throws UnknownWareException {
            return ware.basePrice() * 3;
        }
    };
    public static GuiDisplayableMarket mockMarket4 = new GuiDisplayableMarket() {
        @Override
        public String getName() {
            return "mockMarket4";
        }

        @Override
        public ObservableMap<Ware, Integer> getInventory() {
            return mockMarket4Iventory;
        }

        @Override
        public int getLocalPrice(Ware ware) throws UnknownWareException {
            return ware.basePrice() * 4;
        }
    };
    public static List<GuiDisplayableMarket> mockMarkets2 = new ArrayList<>();
    public static final SimpleObjectProperty<GuiDisplayableMarket> mockState2CurrentMarket = new SimpleObjectProperty<>(mockMarket3);
    public static GuiDisplayableGameState mockState2 = new GuiDisplayableGameState() {
        @Override
        public GuiDisplayablePlayer getPlayer() {
            return mockPlayer2;
        }

        @Override
        public List<? extends GuiDisplayableMarket> getMarkets() {
            return mockMarkets2;
        }

        @Override
        public int getDistance(GuiDisplayableMarket from, GuiDisplayableMarket to) {
            return 15;
        }

        @Override
        public SimpleObjectProperty<? extends GuiDisplayableMarket> getCurrentMarket() {
            return mockState2CurrentMarket;
        }

        @Override
        public int getWinBalance() {
            return 1000000;
        }
    };
    static {
        mockPlayer2Iventory.put(brick, 11);
        mockPlayer2Iventory.put(mortar, 22);
        mockMarket3Inventory.put(brick, 33);
        mockMarket3Inventory.put(mortar, 44);
        mockMarket4Iventory.put(brick, 55);
        mockMarket4Iventory.put(mortar, 66);
        mockMarkets2.add(mockMarket3);
        mockMarkets2.add(mockMarket4);
    }

    public static final Queue<Callable<Void>> tasks = new LinkedList<>();
    static {
        tasks.add(() -> {
            mockState1.getPlayer().getInventory().put(MainGUI.pen, 5);
            return null;
        });
        tasks.add(() -> {
            mockMarket1Inventory.put(MainGUI.paper, 10);
            return null;
        });
        tasks.add(() -> {
            mockState1CurrentMarket.set(mockMarket2);
            return null;
        });
        tasks.add(() -> {
            mockMarket1Inventory.put(MainGUI.paper, 30);
            return null;
        });
        tasks.add(() -> {
            mockMarket2Inventory.put(MainGUI.paper, 40);
            return null;
        });
        tasks.add(() -> {
            mockState1CurrentMarket.set(mockMarket1);
            return null;
        });

        tasks.add(() -> {
            GuiImpl.getInstance().setState(mockState2);
            return null;
        });


        tasks.add(() -> {
            mockMarket1Inventory.put(MainGUI.paper, 300);
            return null;
        });
        tasks.add(() -> {
            mockMarket2Inventory.put(MainGUI.paper, 400);
            return null;
        });
        tasks.add(() -> {
            mockState1.getPlayer().getInventory().put(MainGUI.pen, 5555);
            return null;
        });
        tasks.add(() -> {
            mockState2.getPlayer().getInventory().put(MainGUI.brick, 55);
            return null;
        });
        tasks.add(() -> {
            mockMarket3Inventory.put(MainGUI.mortar, 100);
            return null;
        });
        tasks.add(() -> {
            mockState2CurrentMarket.set(mockMarket4);
            return null;
        });
        tasks.add(() -> {
            mockMarket3Inventory.put(MainGUI.mortar, 30);
            return null;
        });
        tasks.add(() -> {
            mockMarket4Iventory.put(MainGUI.mortar, 40);
            return null;
        });


        tasks.add(() -> {
            Platform.exit();
            return null;
        });
    }

    public static void main(String[] args) {

        GuiImpl.launch(mockState1);
    }
}
