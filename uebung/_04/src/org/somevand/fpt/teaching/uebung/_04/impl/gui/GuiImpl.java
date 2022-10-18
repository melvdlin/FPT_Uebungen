package org.somevand.fpt.teaching.uebung._04.impl.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.somevand.fpt.teaching.uebung._04.exceptions.UnknownMarketException;
import org.somevand.fpt.teaching.uebung._04.gui.GUI;
import org.somevand.fpt.teaching.uebung._04.gui.GuiDisplayableGameState;
import org.somevand.fpt.teaching.uebung._04.gui.GuiDisplayableMarket;
import org.somevand.fpt.teaching.uebung._04.gui.GuiGameController;
import org.somevand.fpt.teaching.uebung._04.data.Ware;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class GuiImpl extends Application implements GUI {
    //region constants
    private static final double SQRT_TWO_HALVES = Math.sqrt(2.0) / 2;
    private static final double INITIAL_HEIGHT = 750;
    private static final double INITIAL_WIDTH = 800;
    private static final double SPACING = 10.0;
    private static final Color  MAP_MARKETCOLOR_BASE                = Color.GREY;
    private static final Color  MAP_MARKETCOLOR_HIGHLIGHT           = Color.GREEN;
    private static final double MAP_BORDERWIDTH                     = SPACING / 2;
    private static final double MAP_INNERSIDELENGTH                 = 500;
    private static final double MAP_SIDELENGTH                      = MAP_INNERSIDELENGTH + 2 * MAP_BORDERWIDTH;
    private static final double MAP_MARKETDIAMETER                  = MAP_INNERSIDELENGTH / 4;
    private static final double MAP_COORD_MARKET_NEAR               = MAP_BORDERWIDTH;
    private static final double MAP_COORD_MARKET_FAR                = MAP_SIDELENGTH - MAP_BORDERWIDTH - MAP_MARKETDIAMETER;
    private static final double MAP_COORD_MARKET_EDGE_NEAR          = MAP_BORDERWIDTH + MAP_MARKETDIAMETER / 2;
    private static final double MAP_COORD_MARKET_EDGE_FAR           = MAP_SIDELENGTH - MAP_COORD_MARKET_EDGE_NEAR;
    private static final double MAP_COORD_PRIMARY_EDGEVAL_NEAR      = MAP_BORDERWIDTH * 2 + MAP_MARKETDIAMETER;
    private static final double MAP_COORD_PRIMARY_EDGEVAL_FAR       = MAP_SIDELENGTH - MAP_COORD_PRIMARY_EDGEVAL_NEAR;
    private static final double MAP_COORD_SECONDARY_EDGEVAL_NEAR    = MAP_COORD_MARKET_EDGE_NEAR - MAP_BORDERWIDTH;
    private static final double MAP_COORD_SECONDARY_EDGEVAL_FAR     = MAP_SIDELENGTH - MAP_COORD_SECONDARY_EDGEVAL_NEAR;
    private static final double MAP_COORD_X_EDGEVAL_DIAG_NEAR       = MAP_COORD_MARKET_EDGE_NEAR + SQRT_TWO_HALVES * MAP_MARKETDIAMETER * 1.0 / 2.0 + 4 * MAP_BORDERWIDTH;
    private static final double MAP_COORD_X_EDGEVAL_DIAG_FAR        = MAP_SIDELENGTH - MAP_COORD_X_EDGEVAL_DIAG_NEAR;
    private static final double MAP_COORD_Y_EDGEVAL_DIAG_NEAR       = MAP_COORD_MARKET_EDGE_NEAR + SQRT_TWO_HALVES * MAP_MARKETDIAMETER * 1.0 / 2.0 + 0 * MAP_BORDERWIDTH;
    private static final double MAP_COORD_Y_EDGEVAL_DIAG_FAR        = MAP_SIDELENGTH - MAP_COORD_Y_EDGEVAL_DIAG_NEAR;
    private static final double MAP_MARKET_NAME_MAX_WIDTH           = MAP_MARKETDIAMETER - MAP_BORDERWIDTH;
    private static final String TAG_BALANCE                         = "Balance:";
    private static final String TAG_CARGOSPACE                      = "Available cargo space:";
    private static final String TAG_FUEL                            = "Remaining fuel:";
    private static final String TAG_WINBALANCE                      = "Balance needed to retire:";
    private static final String TAG_BALANCECURRENCY                 = "c";
    private static final String TAG_CARGOSPACESEPARATOR             = "/";
    private static final String TAG_TEXTFIELD_SERDEPATH             = "Save/Load Path ...";
    private static final String TAG_BUTTON_SAVE                     = "Save";
    private static final String TAG_BUTTON_LOAD                     = "Load";
    private static final String TAG_BUTTON_EXIT                     = "Exit";
    private static final String TAG_BUTTON_POPUP                    = "OK";
    private static final String TAG_COLUMN_WARENAME                 = "Ware";
    private static final String TAG_COLUMN_PRICE                    = "Price/Unit";
    private static final String TAG_COLUMN_SIZE                     = "Size";
    private static final String TAG_COLUMN_COUNT                    = "Available";
    private static final String TAG_MARKETINVENTORY                 = "Market Inventory";
    private static final String TAG_PLAYERINVENTORY                 = "Player Inventory";
    private static final Insets INSETS_BASE                         = new Insets(SPACING, SPACING / 2, 0.0, SPACING / 2);
    private static final Insets INSETS_TOP                          = new Insets(0.0, SPACING / 2, 0.0, SPACING / 2);
    private static final Insets INSETS_TOP_SEPARATION               = new Insets(SPACING * 2, SPACING / 2, 0.0, SPACING / 2);
    //endregion

    //region fields
    private static GuiGameController controller = null;
    private static GuiImpl instance = null;
    private static final SimpleObjectProperty<GuiDisplayableGameState> gameState = new SimpleObjectProperty<>(null);

    //region primary displayed data
    private GuiDisplayableMarket marketNW = null;
    private GuiDisplayableMarket marketNE = null;
    private GuiDisplayableMarket marketSW = null;
    private GuiDisplayableMarket marketSE = null;
    private final ObservableList<InventoryEntry> playerInventoryList = FXCollections.observableArrayList();
    private final ObservableList<InventoryEntry> marketInventoryList = FXCollections.observableArrayList();
    private final Map<Ware, InventoryEntry> playerInventoryEntryMap = FXCollections.observableHashMap();
    private final Map<Ware, InventoryEntry> marketInventoryEntryMap = FXCollections.observableHashMap();
    private final IntegerBinding playerBalance = new IntegerBinding() {
        {
            bind(gameState);
            gameState.addListener((observable, oldValue, newValue) -> {
                if (oldValue != null)
                    unbind(gameState, oldValue.getPlayer().getBalance());
                if (newValue != null)
                    bind(gameState, newValue.getPlayer().getBalance());
            });
        }
        @Override
        protected int computeValue() {
            try {
                return gameState.getValue() == null ? 0 : gameState.getValue().getPlayer().getBalance().getValue().intValue();
            } catch (Exception e) {
                return 0;
            }
        }
    };
    private final IntegerBinding playerCargoSpace = new IntegerBinding() {
        {
            bind(gameState);
            gameState.addListener((observable, oldValue, newValue) -> {
                if (oldValue != null)
                    unbind(gameState, oldValue.getPlayer().getRemainingCapacity());
                if (newValue != null)
                    bind(gameState, newValue.getPlayer().getRemainingCapacity());
            });
        }
        @Override
        protected int computeValue() {
            try {
                return gameState.getValue() == null ? 0 : gameState.getValue().getPlayer().getRemainingCapacity().getValue().intValue();
            } catch (Exception e) {
                return 0;
            }
        }
    };
    private final IntegerBinding playerMaxCargoSpace = Bindings.createIntegerBinding(
            () -> gameState.getValue() == null ? 0 : gameState.getValue().getPlayer().getMaxCapacity(),
            gameState
    );
    private final IntegerBinding playerFuel= new IntegerBinding() {
        {
            bind(gameState);
            gameState.addListener((observable, oldValue, newValue) -> {
                if (oldValue != null)
                    unbind(gameState, oldValue.getPlayer().getFuelReach());
                if (newValue != null)
                    bind(gameState, newValue.getPlayer().getFuelReach());
            });
        }
        @Override
        protected int computeValue() {
            try {
                return gameState.getValue() == null ? 0 : gameState.getValue().getPlayer().getFuelReach().getValue().intValue();
            } catch (Exception e) {
                return 0;
            }
        }
    };
    private final IntegerBinding winBalance = Bindings.createIntegerBinding(
            () -> gameState.getValue() == null ? 0 : gameState.getValue().getWinBalance(),
            gameState
    );
    //endregion

    //region popup data
    private final SimpleStringProperty popupTitle = new SimpleStringProperty();
    private final SimpleStringProperty popupMessage = new SimpleStringProperty();
    //endregion

    //region user configurable listeners
    private final List<Consumer<GuiDisplayableMarket>> marketClickedListeners = new LinkedList<>();
    private final List<Consumer<Ware>> marketWareClickedListeners = new LinkedList<>();
    private final List<Consumer<Ware>> playerWareClickedListeners = new LinkedList<>();
    private final List<Consumer<String>> saveButtonClickedListeners = new LinkedList<>();
    private final List<Consumer<String>> loadButtonClickedListeners = new LinkedList<>();
    private final List<Runnable> exitButtonClickedListeners = new LinkedList<>();
    //endregion

    //region popup listeners
    private Runnable popupButtonClickedListener = null;
    //endregion

    //region stages and scenes
    private Stage primaryStage = null;
    private final Stage popupStage = new Stage();
    private final Scene primaryScene = new Scene(new Region(), INITIAL_WIDTH, INITIAL_HEIGHT);
    private final Scene popupScene = new Scene(new Region());
    //endregion

    //region primary stage nodes
    private final AnchorPane baseAnchorPane                                                                 = new AnchorPane();
        private final HBox baseHBox                                                                         = new HBox();
            private final VBox leftVBox                                                                     = new VBox();
                private final Canvas marketMapCanvas                                                        = new Canvas();
                private final GridPane miscInfoGridPane                                                     = new GridPane();
                    private final Label balanceTagLabel                                                     = new Label();
                    private final Label balanceLabel                                                        = new Label();
                    private final Label cargoSpaceTagLabel                                                  = new Label();
                    private final Label cargoSpaceLabel                                                     = new Label();
                    private final Label fuelTagLabel                                                        = new Label();
                    private final Label fuelLabel                                                           = new Label();
                    private final Label winBalanceTagLabel                                                  = new Label();
                    private final Label winBalanceLabel                                                     = new Label();
                private final Region leftSpacerRegion                                                       = new Region();
                private final TextField serdePathTextField                                                  = new TextField();
                private final HBox serdeButtonHBox                                                          = new HBox();
                    private final Button saveButton                                                         = new Button();
                    private final Button loadButton                                                         = new Button();
                    private final Button exitButton                                                         = new Button();
            private final VBox rightVBox                                                                    = new VBox();
                private final Label marketLabel                                                             = new Label();
                private final TableView<InventoryEntry> marketInventoryTableView                            = new TableView<>();
                    private final TableColumn<InventoryEntry, String> marketNameTableColumn                 = new TableColumn<>();
                    private final TableColumn<InventoryEntry, Number> marketPriceTableColumn                = new TableColumn<>();
                    private final TableColumn<InventoryEntry, Number> marketSizeTableColumn                 = new TableColumn<>();
                    private final TableColumn<InventoryEntry, Number> marketCountTableColumn                = new TableColumn<>();
                private final Label playerLabel                                                             = new Label();
                private final TableView<InventoryEntry> playerInventoryTableView                            = new TableView<>();
                    private final TableColumn<InventoryEntry, String> playerNameTableColumn                 = new TableColumn<>();
                    private final TableColumn<InventoryEntry, Number> playerPriceTableColumn                = new TableColumn<>();
                    private final TableColumn<InventoryEntry, Number> playerSizeTableColumn                 = new TableColumn<>();
                    private final TableColumn<InventoryEntry, Number> playerCountTableColumn                = new TableColumn<>();
    //endregion

    //region popup stage nodes
    private final AnchorPane popupAnchorPane                                                                = new AnchorPane();
        private final VBox popupVBox                                                                        = new VBox();
            private final Text popupMessageText                                                             = new Text();
            private final Button popupButton                                                                = new Button();
    //endregion

    //endregion

    //region methods
    public static GuiImpl getInstance() {
        return instance;
    }

    //region launch methods
    public static void launch() {
        launch((GuiDisplayableGameState) null);
    }

    public static void launch(GuiGameController controller) {
        GuiImpl.controller = Objects.requireNonNull(controller);
        launch((GuiDisplayableGameState) null);
    }

    public static void launch(GuiDisplayableGameState state) {
        staticSetState(state);
        Application.launch();
    }
    //endregion

    //region gameState setters
    private static void staticSetState(GuiDisplayableGameState state) {
        if (gameState.getValue() == state) return;
        gameState.setValue(state);
    }
    @Override
    public void setState(GuiDisplayableGameState state) {
        staticSetState(state);
    }
    //endregion

    // region application init / start / stop methods
    @Override
    public void init() {
        instance = this;
        gameState.addListener(this::onStateChanged);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setScene(primaryScene);
        this.primaryScene.setRoot(baseAnchorPane);

        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(this.primaryStage);
        popupStage.setScene(popupScene);
        popupScene.setRoot(popupAnchorPane);
        popupStage.setResizable(false);

        assembleUINodes();
        assemblePopupNodes();
        setUIStyle();
        setPopupStyle();
        setUIListeners();
        setPopupListeners();
        linkUIToDataModel();
        linkPopupToDataModel();

        if (controller != null) controller.setup(getInstance());

        update();

        // miscInfoGridPane.setGridLinesVisible(true);

        primaryStage.sizeToScene();
        primaryStage.setMinHeight(primaryStage.getHeight());
        primaryStage.setMinWidth(primaryStage.getWidth());
        Platform.setImplicitExit(false);

    }

    @Override
    public void stop() {
        this.primaryStage = null;
    }
    //endregion

    //region stage control methods
    @Override
    public void show() {
        primaryStage.show();
    }

    @Override
    public void hide() {
        primaryStage.hide();
    }

    @Override
    public void exit() {
        Platform.exit();
    }


    @Override
    public void showPopup(String title, String msg, Runnable onConfirmedListener) {
        updatePopup(title, msg, onConfirmedListener);
        popupStage.show();
    }

    @Override
    public void showPopupAndWait(String title, String msg) {
        updatePopup(title, msg, null);
        popupStage.showAndWait();
    }
    //endregion

    //region user configurable listener management methods
    @Override
    public void addOnMarketClickedListener(Consumer<GuiDisplayableMarket> listener) {
        marketClickedListeners.add(listener);
    }

    @Override
    public void removeOnMarketClickedListener(Consumer<GuiDisplayableMarket> listener) {
        marketClickedListeners.remove(listener);
    }

    @Override
    public void addOnMarketWareClickedListener(Consumer<Ware> listener) {
        marketWareClickedListeners.add(listener);
    }

    @Override
    public void removeOnMarketWareClickedListener(Consumer<Ware> listener) {
        marketWareClickedListeners.remove(listener);
    }

    @Override
    public void addOnPlayerWareClickedListener(Consumer<Ware> listener) {
        playerWareClickedListeners.add(listener);
    }

    @Override
    public void removeOnPlayerWareClickedListener(Consumer<Ware> listener) {
        playerWareClickedListeners.remove(listener);
    }

    @Override
    public void addOnSaveButtonClickedListener(Consumer<String> listener) {
        saveButtonClickedListeners.add(listener);
    }

    @Override
    public void removeOnSaveButtonClickedListener(Consumer<String> listener) {
        saveButtonClickedListeners.remove(listener);
    }

    @Override
    public void addOnLoadButtonClickedListener(Consumer<String> listener) {
        loadButtonClickedListeners.add(listener);
    }

    @Override
    public void removeOnLoadButtonClickedListener(Consumer<String> listener) {
        loadButtonClickedListeners.remove(listener);
    }

    @Override
    public void addOnExitButtonClickedListener(Runnable listener) {
        exitButtonClickedListeners.add(listener);
    }

    @Override
    public void removeOnExitButtonClickedListener(Runnable listener) {
        exitButtonClickedListeners.remove(listener);
    }
    //endregion

    //region listener methods
    private void onStateChanged(
            ObservableValue<? extends GuiDisplayableGameState> stateProperty,
            GuiDisplayableGameState oldState,
            GuiDisplayableGameState newState) {
        if (oldState != null)
            oldState.getCurrentMarket().removeListener(this::onCurrentMarketChanged);
        if (newState != null)
            newState.getCurrentMarket().addListener(this::onCurrentMarketChanged);
        update();
    }

    private void onCurrentMarketChanged(Observable observable) {
        updateDisplay();
    }

    private void onSaveButtonClicked(ActionEvent event) {
        for (var listener : saveButtonClickedListeners)
            listener.accept(serdePathTextField.getText());
    }

    private void onLoadButtonClicked(ActionEvent event) {
        for (var listener : loadButtonClickedListeners)
            listener.accept(serdePathTextField.getText());
    }

    private void onExitButtonClicked(ActionEvent event) {
        for (var listener : exitButtonClickedListeners)
            listener.run();
    }

    private void onMarketInventoryRowClicked(MouseEvent event) {
        if (event.getSource() instanceof TableRow<?> tr && tr.getItem() instanceof InventoryEntry entry) {
            for (var listener : marketWareClickedListeners) {
                listener.accept(entry.getWare());
            }
        }
    }

    private void onPlayerInventoryRowClicked(MouseEvent event) {
        if (event.getSource() instanceof TableRow<?> tr && tr.getItem() instanceof InventoryEntry entry) {
            for (var listener : playerWareClickedListeners) {
                listener.accept(entry.getWare());
            }
        }
    }

    private void onMapClicked(MouseEvent event) {
        final double x = event.getX();
        final double y = event.getY();

        GuiDisplayableMarket market = null;

        // distance to center of NW market circle
        final double distToNW = Math.sqrt(
                (x - MAP_COORD_MARKET_EDGE_NEAR) * (x - MAP_COORD_MARKET_EDGE_NEAR) +
                (y - MAP_COORD_MARKET_EDGE_NEAR) * (y - MAP_COORD_MARKET_EDGE_NEAR)
        );
        // distance to center of NE market circle
        final double distToNE = Math.sqrt(
                (x - MAP_COORD_MARKET_EDGE_FAR)  * (x - MAP_COORD_MARKET_EDGE_FAR) +
                (y - MAP_COORD_MARKET_EDGE_NEAR) * (y - MAP_COORD_MARKET_EDGE_NEAR)
        );
        // distance to center of SW market circle
        final double distToSW = Math.sqrt(
                (x - MAP_COORD_MARKET_EDGE_NEAR) * (x - MAP_COORD_MARKET_EDGE_NEAR) +
                (y - MAP_COORD_MARKET_EDGE_FAR)  * (y - MAP_COORD_MARKET_EDGE_FAR)
        );
        // distance to center of SE market circle
        final double distToSE = Math.sqrt(
                (x - MAP_COORD_MARKET_EDGE_FAR)  * (x - MAP_COORD_MARKET_EDGE_FAR) +
                (y - MAP_COORD_MARKET_EDGE_FAR)  * (y - MAP_COORD_MARKET_EDGE_FAR)
        );

        if (distToNW < MAP_MARKETDIAMETER / 2) {
            market = marketNW;
        } else if (distToNE < MAP_MARKETDIAMETER / 2) {
            market = marketNE;
        } else if (distToSW < MAP_MARKETDIAMETER / 2) {
            market = marketSW;
        } else if (distToSE < MAP_MARKETDIAMETER / 2) {
            market = marketSE;
        }

        if (market != null) {
            for (var listener : marketClickedListeners) {
                listener.accept(market);
            }
        }
    }

    private void onPopupButtonClicked(ActionEvent event) {
        if (popupButtonClickedListener != null)
            popupButtonClickedListener.run();
        popupStage.hide();
    }
    //endregion

    // region update methods
    private void update() {
        updateData();
        updateDisplay();
    }

    //region data update methods
    private void updateData() {
        try {
            marketNW = gameState.getValue().getMarkets().get(0);
            marketNE = gameState.getValue().getMarkets().get(1);
            marketSW = gameState.getValue().getMarkets().get(2);
            marketSE = gameState.getValue().getMarkets().get(3);
        } catch (IndexOutOfBoundsException ignored) { }

        marketInventoryList.clear();
        playerInventoryList.clear();
        marketInventoryEntryMap.clear();
        playerInventoryEntryMap.clear();
        populateMarketInventory();
        populatePlayerInventory();
    }

    private void populateMarketInventory() {
        for (var entry : gameState.get().getCurrentMarket().getValue().getInventory().entrySet()) {
            if (marketInventoryEntryMap.containsKey(entry.getKey())) {
                marketInventoryList.add(marketInventoryEntryMap.get(entry.getKey()));
            } else {
                var inventoryEntry = (InventoryEntry) new MarketInventoryEntry(entry.getKey(), gameState.get());
                marketInventoryEntryMap.put(entry.getKey(), inventoryEntry);
                marketInventoryList.add(inventoryEntry);
            }
        }
    }

    private void populatePlayerInventory() {
        for (var entry : gameState.get().getPlayer().getInventory().entrySet()) {
            if (playerInventoryEntryMap.containsKey(entry.getKey())) {
                playerInventoryList.add(playerInventoryEntryMap.get(entry.getKey()));
            } else {
                var inventoryEntry = (InventoryEntry) new PlayerInventoryEntry(entry.getKey(), gameState.get());
                playerInventoryEntryMap.put(entry.getKey(), inventoryEntry);
                playerInventoryList.add(inventoryEntry);
            }
        }
    }

    private void updatePopup(String title, String msg, Runnable onConfirmedListener) {
        popupTitle.set(title);
        popupMessage.set(msg);
        popupButtonClickedListener = onConfirmedListener;
    }
    //endregion

    //region display update methods
    private void updateDisplay() {
        drawMap();
    }

    private void drawMap() {
        var gc = marketMapCanvas.getGraphicsContext2D();
        marketMapCanvas.setWidth(MAP_SIDELENGTH);
        marketMapCanvas.setHeight(MAP_SIDELENGTH);
        gc.clearRect(
                0.0,
                0.0,
                marketMapCanvas.getWidth(),
                marketMapCanvas.getHeight());

        //region edges
        gc.setStroke(Color.BLACK);
        gc.strokeLine(
                MAP_COORD_MARKET_EDGE_NEAR,
                MAP_COORD_MARKET_EDGE_NEAR,
                MAP_COORD_MARKET_EDGE_FAR,
                MAP_COORD_MARKET_EDGE_NEAR);
        gc.strokeLine(
                MAP_COORD_MARKET_EDGE_NEAR,
                MAP_COORD_MARKET_EDGE_FAR,
                MAP_COORD_MARKET_EDGE_FAR,
                MAP_COORD_MARKET_EDGE_FAR);
        gc.strokeLine(
                MAP_COORD_MARKET_EDGE_NEAR,
                MAP_COORD_MARKET_EDGE_NEAR,
                MAP_COORD_MARKET_EDGE_NEAR,
                MAP_COORD_MARKET_EDGE_FAR);
        gc.strokeLine(
                MAP_COORD_MARKET_EDGE_FAR,
                MAP_COORD_MARKET_EDGE_NEAR,
                MAP_COORD_MARKET_EDGE_FAR,
                MAP_COORD_MARKET_EDGE_FAR);
        gc.strokeLine(
                MAP_COORD_MARKET_EDGE_NEAR,
                MAP_COORD_MARKET_EDGE_NEAR,
                MAP_COORD_MARKET_EDGE_FAR,
                MAP_COORD_MARKET_EDGE_FAR);
        gc.strokeLine(
                MAP_COORD_MARKET_EDGE_NEAR,
                MAP_COORD_MARKET_EDGE_FAR,
                MAP_COORD_MARKET_EDGE_FAR,
                MAP_COORD_MARKET_EDGE_NEAR);
        //endregion

        //region node fills
        gc.setFill(MAP_MARKETCOLOR_BASE);
        if (gameState.get() != null && gameState.get().getCurrentMarket().getValue() == marketNW)
            gc.setFill(MAP_MARKETCOLOR_HIGHLIGHT);
        gc.fillOval(
                MAP_COORD_MARKET_NEAR,
                MAP_COORD_MARKET_NEAR,
                MAP_MARKETDIAMETER, MAP_MARKETDIAMETER);
        gc.setFill(MAP_MARKETCOLOR_BASE);
        if (gameState.get() != null && gameState.get().getCurrentMarket().getValue() == marketNE)
            gc.setFill(MAP_MARKETCOLOR_HIGHLIGHT);
        gc.fillOval(
                MAP_COORD_MARKET_FAR,
                MAP_COORD_MARKET_NEAR,
                MAP_MARKETDIAMETER, MAP_MARKETDIAMETER);
        gc.setFill(MAP_MARKETCOLOR_BASE);
        if (gameState.get() != null && gameState.get().getCurrentMarket().getValue() == marketSW)
            gc.setFill(MAP_MARKETCOLOR_HIGHLIGHT);
        gc.fillOval(
                MAP_COORD_MARKET_NEAR,
                MAP_COORD_MARKET_FAR,
                MAP_MARKETDIAMETER, MAP_MARKETDIAMETER);
        gc.setFill(MAP_MARKETCOLOR_BASE);
        if (gameState.get() != null && gameState.get().getCurrentMarket().getValue() == marketSE)
            gc.setFill(MAP_MARKETCOLOR_HIGHLIGHT);
        gc.fillOval(
                MAP_COORD_MARKET_FAR,
                MAP_COORD_MARKET_FAR,
                MAP_MARKETDIAMETER, MAP_MARKETDIAMETER);
        //endregion

        //region node edges
        gc.strokeOval(
                MAP_COORD_MARKET_NEAR,
                MAP_COORD_MARKET_NEAR,
                MAP_MARKETDIAMETER, MAP_MARKETDIAMETER);
        gc.strokeOval(
                MAP_COORD_MARKET_FAR,
                MAP_COORD_MARKET_NEAR,
                MAP_MARKETDIAMETER, MAP_MARKETDIAMETER);
        gc.strokeOval(
                MAP_COORD_MARKET_NEAR,
                MAP_COORD_MARKET_FAR,
                MAP_MARKETDIAMETER, MAP_MARKETDIAMETER);
        gc.strokeOval(
                MAP_COORD_MARKET_FAR,
                MAP_COORD_MARKET_FAR,
                MAP_MARKETDIAMETER, MAP_MARKETDIAMETER);
        //endregion

        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(Font.font(gc.getFont().getFamily(), FontWeight.SEMI_BOLD, FontPosture.REGULAR, 14.0));

        //region market names
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.WHITE);
        if (marketNW != null)
            gc.fillText(marketNW.getName(), MAP_COORD_MARKET_EDGE_NEAR, MAP_COORD_MARKET_EDGE_NEAR, MAP_MARKET_NAME_MAX_WIDTH);
        if (marketNE != null)
            gc.fillText(marketNE.getName(), MAP_COORD_MARKET_EDGE_FAR, MAP_COORD_MARKET_EDGE_NEAR, MAP_MARKET_NAME_MAX_WIDTH);
        if (marketSW != null)
            gc.fillText(marketSW.getName(), MAP_COORD_MARKET_EDGE_NEAR, MAP_COORD_MARKET_EDGE_FAR, MAP_MARKET_NAME_MAX_WIDTH);
        if (marketSE != null)
            gc.fillText(marketSE.getName(), MAP_COORD_MARKET_EDGE_FAR, MAP_COORD_MARKET_EDGE_FAR, MAP_MARKET_NAME_MAX_WIDTH);
        //endregion

        //region market distances
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.setTextBaseline(VPos.TOP);

        try {
            if (marketSW != null && marketNW != null)
                gc.fillText(
                        String.valueOf(gameState.getValue().getDistance(marketSW, marketNW)),
                        MAP_COORD_SECONDARY_EDGEVAL_NEAR,
                        MAP_COORD_PRIMARY_EDGEVAL_NEAR,
                        MAP_MARKET_NAME_MAX_WIDTH);
            if (marketSW != null && marketSE != null)
                gc.fillText(
                        String.valueOf(gameState.getValue().getDistance(marketSW, marketSE)),
                        MAP_COORD_PRIMARY_EDGEVAL_FAR,
                        MAP_COORD_SECONDARY_EDGEVAL_FAR,
                        MAP_MARKET_NAME_MAX_WIDTH);

            gc.setTextBaseline(VPos.BOTTOM);
            if (marketNW != null && marketSW != null)
                gc.fillText(
                        String.valueOf(gameState.getValue().getDistance(marketNW, marketSW)),
                        MAP_COORD_SECONDARY_EDGEVAL_NEAR,
                        MAP_COORD_PRIMARY_EDGEVAL_FAR,
                        MAP_MARKET_NAME_MAX_WIDTH);
            if (marketNW != null && marketNE != null)
                gc.fillText(
                        String.valueOf(gameState.getValue().getDistance(marketNW, marketNE)),
                        MAP_COORD_PRIMARY_EDGEVAL_FAR,
                        MAP_COORD_SECONDARY_EDGEVAL_NEAR,
                        MAP_MARKET_NAME_MAX_WIDTH);

            gc.setTextAlign(TextAlignment.LEFT);
            gc.setTextBaseline(VPos.TOP);
            if (marketSE != null && marketNE != null)
                gc.fillText(String.valueOf(
                        gameState.getValue().getDistance(marketSE, marketNE)),
                        MAP_COORD_SECONDARY_EDGEVAL_FAR,
                        MAP_COORD_PRIMARY_EDGEVAL_NEAR,
                        MAP_MARKET_NAME_MAX_WIDTH);
            if (marketSE != null && marketSW != null)
                gc.fillText(String.valueOf(
                        gameState.getValue().getDistance(marketSE, marketSW)),
                        MAP_COORD_PRIMARY_EDGEVAL_NEAR,
                        MAP_COORD_SECONDARY_EDGEVAL_FAR,
                        MAP_MARKET_NAME_MAX_WIDTH);
            gc.setTextBaseline(VPos.BOTTOM);
            if (marketNE != null && marketSE != null)
                gc.fillText(String.valueOf(
                        gameState.getValue().getDistance(marketNE, marketSE)),
                        MAP_COORD_SECONDARY_EDGEVAL_FAR,
                        MAP_COORD_PRIMARY_EDGEVAL_FAR,
                        MAP_MARKET_NAME_MAX_WIDTH);
            if (marketNE != null && marketNW != null)
                gc.fillText(String.valueOf(
                        gameState.getValue().getDistance(marketNE, marketNW)),
                        MAP_COORD_PRIMARY_EDGEVAL_NEAR,
                        MAP_COORD_SECONDARY_EDGEVAL_NEAR,
                        MAP_MARKET_NAME_MAX_WIDTH);

            gc.setTextBaseline(VPos.CENTER);
            gc.setTextAlign(TextAlignment.LEFT);
            if (marketSE != null && marketNW != null)
                gc.fillText(String.valueOf(gameState.getValue().getDistance(marketSE, marketNW)),
                        MAP_COORD_X_EDGEVAL_DIAG_NEAR,
                        MAP_COORD_Y_EDGEVAL_DIAG_NEAR,
                        MAP_MARKET_NAME_MAX_WIDTH);
            if (marketNE != null && marketSW != null)
                gc.fillText(String.valueOf(gameState.getValue().getDistance(marketNE, marketSW)),
                        MAP_COORD_X_EDGEVAL_DIAG_NEAR,
                        MAP_COORD_Y_EDGEVAL_DIAG_FAR,
                        MAP_MARKET_NAME_MAX_WIDTH);
            gc.setTextAlign(TextAlignment.RIGHT);
            if (marketSW != null && marketNE != null)
                gc.fillText(String.valueOf(gameState.getValue().getDistance(marketSW, marketNE)),
                        MAP_COORD_X_EDGEVAL_DIAG_FAR,
                        MAP_COORD_Y_EDGEVAL_DIAG_NEAR,
                        MAP_MARKET_NAME_MAX_WIDTH);
            if (marketNW != null && marketSE != null)
                gc.fillText(String.valueOf(gameState.getValue().getDistance(marketNW, marketSE)),
                        MAP_COORD_X_EDGEVAL_DIAG_FAR,
                        MAP_COORD_Y_EDGEVAL_DIAG_FAR,
                        MAP_MARKET_NAME_MAX_WIDTH);
        } catch (UnknownMarketException e) {
            throw new RuntimeException(e);
        }
        //endregion
    }
    //endregion
    // endregion

    //region UI initialisation methods
    private void assembleUINodes() {
        baseAnchorPane.getChildren().add(baseHBox);
        baseHBox.getChildren().add(leftVBox);
        leftVBox.getChildren().add(marketMapCanvas);
        leftVBox.getChildren().add(miscInfoGridPane);
        miscInfoGridPane.add(balanceTagLabel, 0, 0);
        miscInfoGridPane.add(balanceLabel, 1, 0);
        miscInfoGridPane.add(cargoSpaceTagLabel, 0, 1);
        miscInfoGridPane.add(cargoSpaceLabel, 1, 1);
        miscInfoGridPane.add(fuelLabel, 0, 2);
        miscInfoGridPane.add(fuelTagLabel, 1, 2);
        miscInfoGridPane.add(winBalanceTagLabel, 0, 3);
        miscInfoGridPane.add(winBalanceLabel, 1, 3);
        leftVBox.getChildren().add(leftSpacerRegion);
        leftVBox.getChildren().add(serdePathTextField);
        leftVBox.getChildren().add(serdeButtonHBox);
        serdeButtonHBox.getChildren().add(saveButton);
        serdeButtonHBox.getChildren().add(loadButton);
        serdeButtonHBox.getChildren().add(exitButton);
        baseHBox.getChildren().add(rightVBox);
        rightVBox.getChildren().add(marketLabel);
        rightVBox.getChildren().add(marketInventoryTableView);
        marketInventoryTableView.getColumns().add(marketNameTableColumn);
        marketInventoryTableView.getColumns().add(marketPriceTableColumn);
        marketInventoryTableView.getColumns().add(marketSizeTableColumn);
        marketInventoryTableView.getColumns().add(marketCountTableColumn);
        rightVBox.getChildren().add(playerLabel);
        rightVBox.getChildren().add(playerInventoryTableView);
        playerInventoryTableView.getColumns().add(playerNameTableColumn);
        playerInventoryTableView.getColumns().add(playerPriceTableColumn);
        playerInventoryTableView.getColumns().add(playerSizeTableColumn);
        playerInventoryTableView.getColumns().add(playerCountTableColumn);
    }

    private void assemblePopupNodes() {
        popupAnchorPane.getChildren().add(popupVBox);
        popupVBox.getChildren().add(popupMessageText);
        popupVBox.getChildren().add(popupButton);
    }

    private void setUIStyle() {
        balanceTagLabel.setText(TAG_BALANCE);
        cargoSpaceTagLabel.setText(TAG_CARGOSPACE);
        fuelTagLabel.setText(TAG_FUEL);
        winBalanceTagLabel.setText(TAG_WINBALANCE);
        marketLabel.setText(TAG_MARKETINVENTORY);
        playerLabel.setText(TAG_PLAYERINVENTORY);
        marketNameTableColumn.setText(TAG_COLUMN_WARENAME);
        marketPriceTableColumn.setText(TAG_COLUMN_PRICE);
        marketSizeTableColumn.setText(TAG_COLUMN_SIZE);
        marketCountTableColumn.setText(TAG_COLUMN_COUNT);
        playerNameTableColumn.setText(TAG_COLUMN_WARENAME);
        playerPriceTableColumn.setText(TAG_COLUMN_PRICE);
        playerSizeTableColumn.setText(TAG_COLUMN_SIZE);
        playerCountTableColumn.setText(TAG_COLUMN_COUNT);

        marketNameTableColumn.prefWidthProperty().bind(playerNameTableColumn.widthProperty());
        marketPriceTableColumn.prefWidthProperty().bind(playerPriceTableColumn.widthProperty());
        marketSizeTableColumn.prefWidthProperty().bind(playerSizeTableColumn.widthProperty());
        marketCountTableColumn.prefWidthProperty().bind(playerCountTableColumn.widthProperty());
        playerNameTableColumn.prefWidthProperty().bind(marketNameTableColumn.widthProperty());
        playerPriceTableColumn.prefWidthProperty().bind(marketPriceTableColumn.widthProperty());
        playerSizeTableColumn.prefWidthProperty().bind(marketSizeTableColumn.widthProperty());
        playerCountTableColumn.prefWidthProperty().bind(marketCountTableColumn.widthProperty());

        final Callback<TableColumn<InventoryEntry, Number>, TableCell<InventoryEntry, Number>> baseCF = marketPriceTableColumn.getCellFactory();
        final Callback<TableColumn<InventoryEntry, Number>, TableCell<InventoryEntry, Number>> rightAlignCF = param -> {
            var cell = baseCF.call(param);
            cell.setAlignment(Pos.CENTER_RIGHT);
            return cell;
        };
        marketPriceTableColumn.setCellFactory(rightAlignCF);
        marketSizeTableColumn.setCellFactory(rightAlignCF);
        marketCountTableColumn.setCellFactory(rightAlignCF);
        playerPriceTableColumn.setCellFactory(rightAlignCF);
        playerSizeTableColumn.setCellFactory(rightAlignCF);
        playerCountTableColumn.setCellFactory(rightAlignCF);

        saveButton.setText(TAG_BUTTON_SAVE);
        loadButton.setText(TAG_BUTTON_LOAD);
        exitButton.setText(TAG_BUTTON_EXIT);

        VBox.setMargin(miscInfoGridPane, INSETS_BASE);
        VBox.setMargin(serdePathTextField, INSETS_TOP_SEPARATION);
        VBox.setMargin(serdeButtonHBox, INSETS_BASE);
        VBox.setMargin(marketLabel, INSETS_TOP);
        VBox.setMargin(playerLabel, INSETS_TOP_SEPARATION);
        VBox.setMargin(marketInventoryTableView, INSETS_BASE);
        VBox.setMargin(playerInventoryTableView, INSETS_BASE);

        AnchorPane.setBottomAnchor(baseHBox, SPACING * 1.5);
        AnchorPane.setLeftAnchor(baseHBox, SPACING);
        AnchorPane.setRightAnchor(baseHBox, SPACING);
        AnchorPane.setTopAnchor(baseHBox, SPACING);

        serdeButtonHBox.setSpacing(SPACING);

        HBox.setHgrow(leftVBox, Priority.NEVER);
        HBox.setHgrow(rightVBox, Priority.SOMETIMES);
        HBox.setHgrow(serdePathTextField, Priority.ALWAYS);
        HBox.setHgrow(saveButton, Priority.ALWAYS);
        HBox.setHgrow(loadButton, Priority.ALWAYS);
        HBox.setHgrow(exitButton, Priority.ALWAYS);
        VBox.setVgrow(leftSpacerRegion, Priority.ALWAYS);

        miscInfoGridPane.setHgap(SPACING);
        miscInfoGridPane.setVgap(SPACING);

        serdeButtonHBox.setAlignment(Pos.CENTER);
        saveButton.setAlignment(Pos.CENTER);
        saveButton.setMaxWidth(Double.MAX_VALUE);
        loadButton.setAlignment(Pos.CENTER);
        loadButton.setMaxWidth(Double.MAX_VALUE);
        exitButton.setAlignment(Pos.CENTER);
        exitButton.setMaxWidth(Double.MAX_VALUE);

        serdePathTextField.setPromptText(TAG_TEXTFIELD_SERDEPATH);

        marketLabel.setUnderline(true);
        playerLabel.setUnderline(true);

        GridPane.setConstraints(
                balanceTagLabel,
                0,
                0,
                1,
                1,
                HPos.LEFT,
                VPos.CENTER,
                Priority.SOMETIMES,
                Priority.NEVER);

        GridPane.setConstraints(
                cargoSpaceTagLabel,
                0,
                1,
                1,
                1,
                HPos.LEFT,
                VPos.CENTER,
                Priority.SOMETIMES,
                Priority.NEVER);

        GridPane.setConstraints(
                fuelTagLabel,
                0,
                2,
                1,
                1,
                HPos.LEFT,
                VPos.CENTER,
                Priority.SOMETIMES,
                Priority.NEVER);

        GridPane.setConstraints(
                winBalanceTagLabel,
                0,
                3,
                1,
                1,
                HPos.LEFT,
                VPos.CENTER,
                Priority.SOMETIMES,
                Priority.NEVER);

        GridPane.setConstraints(
                balanceLabel,
                1,
                0,
                1,
                1,
                HPos.RIGHT,
                VPos.CENTER,
                Priority.ALWAYS,
                Priority.NEVER);

        GridPane.setConstraints(
                cargoSpaceLabel,
                1,
                1,
                1,
                1,
                HPos.RIGHT,
                VPos.CENTER,
                Priority.ALWAYS,
                Priority.NEVER);

        GridPane.setConstraints(
                fuelLabel,
                1,
                2,
                1,
                1,
                HPos.RIGHT,
                VPos.CENTER,
                Priority.ALWAYS,
                Priority.NEVER);

        GridPane.setConstraints(
                winBalanceLabel,
                1,
                3,
                1,
                1,
                HPos.RIGHT,
                VPos.CENTER,
                Priority.ALWAYS,
                Priority.NEVER);
    }

    private void setPopupStyle() {
        AnchorPane.setBottomAnchor(popupVBox, SPACING * 1.5);
        AnchorPane.setLeftAnchor(popupVBox, SPACING);
        AnchorPane.setRightAnchor(popupVBox, SPACING);
        AnchorPane.setTopAnchor(popupVBox, SPACING);

        VBox.setMargin(popupMessageText, INSETS_TOP);
        VBox.setMargin(popupButton, INSETS_BASE);
        VBox.setVgrow(popupMessageText, Priority.SOMETIMES);
        VBox.setVgrow(popupButton, Priority.NEVER);

        popupVBox.setAlignment(Pos.CENTER);
        popupMessageText.setTextAlignment(TextAlignment.CENTER);

        popupButton.setText(TAG_BUTTON_POPUP);
    }

    private void linkUIToDataModel() {
        marketInventoryTableView.setItems(marketInventoryList);
        playerInventoryTableView.setItems(playerInventoryList);
        balanceLabel.textProperty().bind(StringBinding.stringExpression(playerBalance).concat(TAG_BALANCECURRENCY));
        cargoSpaceLabel.textProperty().bind(
                StringBinding.stringExpression(playerCargoSpace)
                        .concat(TAG_CARGOSPACESEPARATOR)
                        .concat(playerMaxCargoSpace));
        fuelLabel.textProperty().bind(StringBinding.stringExpression(playerFuel));
        winBalanceLabel.textProperty().bind(StringBinding.stringExpression(winBalance).concat(TAG_BALANCECURRENCY));

        marketNameTableColumn.setCellValueFactory(entry -> entry.getValue().getName());
        marketPriceTableColumn.setCellValueFactory(entry -> entry.getValue().getPrice());
        marketSizeTableColumn.setCellValueFactory(entry -> entry.getValue().getSize());
        marketCountTableColumn.setCellValueFactory(entry -> entry.getValue().getCount());
        playerNameTableColumn.setCellValueFactory(entry -> entry.getValue().getName());
        playerPriceTableColumn.setCellValueFactory(entry -> entry.getValue().getPrice());
        playerSizeTableColumn.setCellValueFactory(entry -> entry.getValue().getSize());
        playerCountTableColumn.setCellValueFactory(entry -> entry.getValue().getCount());
    }

    private void linkPopupToDataModel() {
        popupStage.titleProperty().bind(popupTitle);
        popupMessageText.textProperty().bind(popupMessage);
    }

    private void setUIListeners() {
        marketInventoryTableView.setRowFactory(param -> new TableRow<>() {{
            setOnMouseClicked(GuiImpl.this::onMarketInventoryRowClicked);
        }});
        playerInventoryTableView.setRowFactory(param -> new TableRow<>() {{
            setOnMouseClicked(GuiImpl.this::onPlayerInventoryRowClicked);
        }});

        saveButton.setOnAction(this::onSaveButtonClicked);
        loadButton.setOnAction(this::onLoadButtonClicked);
        exitButton.setOnAction(this::onExitButtonClicked);

        marketMapCanvas.setOnMouseClicked(this::onMapClicked);

        primaryStage.setOnCloseRequest((event) -> {
            event.consume();
            exitButton.fire();
        });
    }

    private void setPopupListeners() {
        popupButton.setOnAction(this::onPopupButtonClicked);
        popupStage.setOnCloseRequest(event -> popupButton.fire());
    }
    //endregion

    //endregion
}
