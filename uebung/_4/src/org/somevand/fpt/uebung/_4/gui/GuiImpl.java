package org.somevand.fpt.uebung._4.gui;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.somevand.fpt.uebung._4.MainGUI;
import org.somevand.fpt.uebung._4.data.Ware;
import org.somevand.fpt.uebung._4.exceptions.UnknownWareException;

import java.util.Map;

public class GuiImpl extends Application implements GUI {

    //region Constants
    private static final double INITIAL_HEIGHT = 750;
    private static final double INITIAL_WIDTH = 900;
    private static final double SPACING = 10.0;
    private static final Color  MAP_MARKETCOLOR_BASE            = Color.GREY;
    private static final Color  MAP_MARKETCOLOR_HIGHLIGHT       = Color.GREEN;
    private static final double MAP_BORDERWIDTH                 = SPACING / 2;
    private static final double MAP_INNERSIDELENGTH             = 500;
    private static final double MAP_SIDELENGTH                  = MAP_INNERSIDELENGTH + 2 * MAP_BORDERWIDTH;
    private static final double MAP_MARKETDIAMETER              = MAP_INNERSIDELENGTH / 4;
    private static final double MAP_COORD_MARKET_NEAR           = MAP_BORDERWIDTH;
    private static final double MAP_COORD_MARKET_FAR            = MAP_SIDELENGTH - MAP_BORDERWIDTH - MAP_MARKETDIAMETER;
    private static final double MAP_COORD_MARKET_EDGE_NEAR      = MAP_MARKETDIAMETER / 2;
    private static final double MAP_COORD_MARKET_EDGE_FAR       = MAP_SIDELENGTH - MAP_COORD_MARKET_EDGE_NEAR;
    private static final String TAG_BALANCE                     = "Balance:";
    private static final String TAG_CARGOSPACE                  = "Available cargo space:";
    private static final String TAG_FUEL                        = "Remaining fuel:";
    private static final String TAG_WINBALANCE                  = "Balance needed to retire:";
    private static final String TAG_TEXTFIELD_SERDEPATH         = "Save/Load Path ...";
    private static final String TAG_BUTTON_SAVE                 = "Save";
    private static final String TAG_BUTTON_LOAD                 = "Load";
    private static final String TAG_BUTTON_EXIT                 = "Exit";
    private static final String TAG_COLUMN_WARENAME             = "Ware";
    private static final String TAG_COLUMN_PRICE                = "Price/Unit";
    private static final String TAG_COLUMN_SIZE                 = "Size";
    private static final String TAG_COLUMN_COUNT                = "Available";
    private static final String TAG_MARKETINVENTORY             = "Market Inventory";
    private static final String TAG_PLAYERINVENTORY             = "Player Inventory";
    private static final Insets INSETS_BASE                     = new Insets(SPACING, SPACING / 2, 0.0, SPACING / 2);
    private static final Insets INSETS_TOP                      = new Insets(SPACING * 2, SPACING / 2, 0.0, SPACING / 2);
    //endregion

    private static GuiImpl instance = null;

    private static final SimpleObjectProperty<GuiDisplayableGameState> gameState = new SimpleObjectProperty<>(null);

    private Stage primaryStage = null;
    private final Scene primaryScene = new Scene(new Region(), INITIAL_WIDTH, INITIAL_HEIGHT);

    //region Nodes
    private final AnchorPane baseAnchorPane                                                     = new AnchorPane();
        private final HBox baseHBox                                                             = new HBox();
            private final VBox leftVBox                                                         = new VBox();
                private final Canvas marketMapCanvas                                            = new Canvas();
                private final GridPane miscInfoGridPane                                         = new GridPane();
                    private final Label balanceTagLabel                                         = new Label(TAG_BALANCE);
                    private final Label balanceLabel                                            = new Label();
                    private final Label cargoSpaceTagLabel                                      = new Label(TAG_CARGOSPACE);
                    private final Label cargoSpaceLabel                                         = new Label();
                    private final Label fuelTagLabel                                            = new Label(TAG_FUEL);
                    private final Label fuelLabel                                               = new Label();
                    private final Label winBalanceTagLabel                                      = new Label(TAG_WINBALANCE);
                    private final Label winBalanceLabel                                         = new Label();
                private final Region leftSpacerRegion                                           = new Region();
                private final TextField serdePathTextField                                      = new TextField();
                private final HBox serdeButtonHBox                                              = new HBox();
                    private final Button saveButton                                             = new Button(TAG_BUTTON_SAVE);
                    private final Button loadButton                                             = new Button(TAG_BUTTON_LOAD);
                    private final Button exitButton                                             = new Button(TAG_BUTTON_EXIT);
            private final VBox rightVBox                                                        = new VBox();
                private final Label marketLabel                                                 = new Label(TAG_MARKETINVENTORY);
                private final TableView<InventoryEntry> marketInventoryTableView                = new TableView<>();
                    private final TableColumn<InventoryEntry, String> marketNameTableColumn     = new TableColumn<>(TAG_COLUMN_WARENAME);
                    private final TableColumn<InventoryEntry, Number> marketPriceTableColumn    = new TableColumn<>(TAG_COLUMN_PRICE);
                    private final TableColumn<InventoryEntry, Number> marketSizeTableColumn     = new TableColumn<>(TAG_COLUMN_SIZE);
                    private final TableColumn<InventoryEntry, Number> marketCountTableColumn    = new TableColumn<>(TAG_COLUMN_COUNT);
                private final Label playerLabel                                                 = new Label(TAG_PLAYERINVENTORY);
                private final TableView<InventoryEntry> playerInventoryTableView                = new TableView<>();
                    private final TableColumn<InventoryEntry, String> playerNameTableColumn     = new TableColumn<>(TAG_COLUMN_WARENAME);
                    private final TableColumn<InventoryEntry, Number> playerPriceTableColumn    = new TableColumn<>(TAG_COLUMN_PRICE);
                    private final TableColumn<InventoryEntry, Number> playerSizeTableColumn     = new TableColumn<>(TAG_COLUMN_SIZE);
                    private final TableColumn<InventoryEntry, Number> playerCountTableColumn    = new TableColumn<>(TAG_COLUMN_COUNT);
    //endregion
    //region Displayed Data
    private GuiDisplayableMarket marketNW = null;
    private GuiDisplayableMarket marketNE = null;
    private GuiDisplayableMarket marketSW = null;
    private GuiDisplayableMarket marketSE = null;
    private final ObservableList<InventoryEntry> playerInventoryList = FXCollections.observableArrayList();
    private final ObservableList<InventoryEntry> marketInventoryList = FXCollections.observableArrayList();
    private final Map<Ware, InventoryEntry> playerInventoryEntryMap = FXCollections.observableHashMap();
    private final Map<Ware, InventoryEntry> marketInventoryEntryMap = FXCollections.observableHashMap();
    //endregion

    public static GuiImpl getInstance() {
        return instance;
    }

    public static GUI launch(GuiDisplayableGameState state) {
        staticSetState(state);
        Application.launch();
        return getInstance();
    }

    public static GUI launch() {
        return launch((GuiDisplayableGameState) null);
    }

    @Override
    public void init() {
        instance = this;
        gameState.addListener(this::onStateChanged);

        marketInventoryTableView.setItems(marketInventoryList);
        playerInventoryTableView.setItems(playerInventoryList);

        exitButton.setOnAction(event -> {
            gameState.get().getPlayer().getInventory().put(MainGUI.pen, 5);
            gameState.get().getCurrentMarket().getValue().getInventory().put(MainGUI.paper, 10);
        });

        onStateChanged(gameState, null, gameState.get());
        // TODO: implement
    }

    @Override
    public void stop() {
        // TODO: implement
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setScene(primaryScene);
        this.primaryScene.setRoot(baseAnchorPane);

        buildUI();
        drawMap();
        // playerInventoryList.add(new PlayerInventoryEntry(MainGUI.pen, gameState.get().getCurrentMarket(), MainGUI.mockPlayer));

//        miscInfoGridPane.setGridLinesVisible(true);

        primaryStage.show();
        primaryStage.setMinHeight(primaryStage.getHeight());
        primaryStage.setMinWidth(primaryStage.getWidth());
    }

    public void setState(GuiDisplayableGameState state) {
        staticSetState(state);
        // TODO: implement
    }

    @Override
    public void show() {
        // TODO: implement
    }

    @Override
    public void hide() {
        // TODO: implement
    }

    @Override
    public void update() {
        drawMap();

        marketInventoryList.clear();
        playerInventoryList.clear();
        marketInventoryEntryMap.clear();
        playerInventoryEntryMap.clear();
        populateMarketInventory();
        populatePlayerInventory();
        try {
            updateInventoryPrices();
        } catch (UnknownWareException e) {
            throw new RuntimeException(e);
        }
        updateMarketInventoryCounts();
        updatePlayerInventoryCounts();

    }

    @Override
    public void showPopup(String title, String msg) {
        // TODO: implement
    }

    @Override
    public void showPopupSync(String title, String msg) {
        // TODO: implement
    }

    @Override
    public void addOnMarketClickedListener(Callback<? extends GuiDisplayableMarket, Void> listener) {
        // TODO: implement
    }

    @Override
    public void removeOnMarketClickedListener(Callback<? extends GuiDisplayableMarket, Void> listener) {
        // TODO: implement
    }

    @Override
    public void addOnMarketWareClickedListener(Callback<Ware, Void> listener) {
        // TODO: implement
    }

    @Override
    public void removeOnMarketWareClickedListener(Callback<Ware, Void> listener) {
        // TODO: implement
    }

    @Override
    public void addOnPlayerWareClickedListener(Callback<Ware, Void> listener) {
        // TODO: implement
    }

    @Override
    public void removeOnPlayerWareClickedListener(Callback<Ware, Void> listener) {
        // TODO: implement
    }

    @Override
    public void addOnSaveButtonClickedListener(Callback<String, Void> listener) {
        // TODO: implement
    }

    @Override
    public void removeOnSaveButtonClickedListener(Callback<String, Void> listener) {
        // TODO: implement
    }

    @Override
    public void addOnLoadButtonClickedListener(Callback<String, Void> listener) {
        // TODO: implement
    }

    @Override
    public void removeOnLoadButtonClickedListener(Callback<String, Void> listener) {
        // TODO: implement
    }

    @Override
    public void addOnExitButtonClickedListener(Callback<Void, Void> listener) {
        // TODO: implement
    }

    @Override
    public void removeOnExitButtonClickedListener(Callback<Void, Void> listener) {
        // TODO: implement
    }

    private static void staticSetState(GuiDisplayableGameState state) {
        if (gameState.get() == state) return;
        gameState.set(state);
    }

    private void onStateChanged(
            ObservableValue<? extends GuiDisplayableGameState> stateProperty,
            GuiDisplayableGameState oldState,
            GuiDisplayableGameState newState) {

        //region deregister listeners from old state
        if (oldState != null) {
            oldState.getCurrentMarket().removeListener(this::onCurrentMarketChanged);
            oldState.getCurrentMarket().getValue().getInventory().removeListener(this::onMarketInventoryChanged);
            oldState.getPlayer().getInventory().removeListener(this::onPlayerInventoryChanged);
        }
        //endregion

        //region register listeners on new state
        if (newState != null) {
            newState.getCurrentMarket().addListener(this::onCurrentMarketChanged);
            newState.getCurrentMarket().getValue().getInventory().addListener(this::onMarketInventoryChanged);
            newState.getPlayer().getInventory().addListener(this::onPlayerInventoryChanged);
        }
        //endregion

        update();
    }

    private void onCurrentMarketChanged(
            ObservableValue<? extends GuiDisplayableMarket> market,
            GuiDisplayableMarket oldMarket,
            GuiDisplayableMarket newMarket) {

        //region deregister inventory listener from old market
        if (oldMarket != null) {
            oldMarket.getInventory().removeListener(this::onMarketInventoryChanged);
        }
        //endregion

        //region register inventory listener on new market
        if (newMarket != null) {
            newMarket.getInventory().addListener(this::onMarketInventoryChanged);
        }

        marketInventoryList.clear();
        populateMarketInventory();
        drawMap();
    }

    private void onMarketInventoryChanged(MapChangeListener.Change<? extends Ware, ? extends Integer> change) {
        if (change.wasAdded()) {
            marketInventoryEntryMap.get(change.getKey()).countProperty().set(change.getValueAdded());
        } else if (change.wasRemoved()) {
            marketInventoryList.remove(marketInventoryEntryMap.get(change.getKey()));
        }
    }

    private void onPlayerInventoryChanged(MapChangeListener.Change<? extends Ware, ? extends Integer> change) {
        if (change.wasAdded()) {
            playerInventoryEntryMap.get(change.getKey()).countProperty().set(change.getValueAdded());
        } else if (change.wasRemoved()) {
            playerInventoryList.remove(playerInventoryEntryMap.get(change.getKey()));
        }
    }

    private void populateMarketInventory() {
        for (var entry : gameState.get().getCurrentMarket().getValue().getInventory().entrySet()) {
            if (marketInventoryEntryMap.containsKey(entry.getKey())) {
                marketInventoryList.add(marketInventoryEntryMap.get(entry.getKey()));
            } else {
                var inventoryEntry = new InventoryEntry(entry.getKey(), gameState.get().getCurrentMarket());
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
                var inventoryEntry = new InventoryEntry(entry.getKey(), gameState.get().getCurrentMarket());
                playerInventoryEntryMap.put(entry.getKey(), inventoryEntry);
                playerInventoryList.add(inventoryEntry);
            }
        }
    }

    private void updateInventoryPrices() throws UnknownWareException {
        for (var entry : marketInventoryList) {
            entry.priceProperty().set(gameState.get().getCurrentMarket().getValue().getLocalPrice(entry.getWare()));
        }
        for (var entry : playerInventoryList) {
            entry.priceProperty().set(gameState.get().getCurrentMarket().getValue().getLocalPrice(entry.getWare()));
        }
    }

    private void updateMarketInventoryCounts() {
        for (var entry : marketInventoryList) {
            updateMarketInventoryCount(entry);
        }
    }

    private void updatePlayerInventoryCounts() {
        for (var entry : playerInventoryList) {
            updatePlayerInventoryCount(entry);
        }
    }

    private void updateMarketInventoryCount(InventoryEntry entry) {
        entry.countProperty().set(gameState.get().getCurrentMarket().getValue().getInventory().get(entry.getWare()));
    }

    private void updatePlayerInventoryCount(InventoryEntry entry) {
        entry.countProperty().set(gameState.get().getPlayer().getInventory().get(entry.getWare()));
    }

    private void buildUI() {
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

        marketNameTableColumn.setCellValueFactory(entry -> entry.getValue().nameProperty());
        marketPriceTableColumn.setCellValueFactory(entry -> entry.getValue().priceProperty());
        marketSizeTableColumn.setCellValueFactory(entry -> entry.getValue().sizeProperty());
        marketCountTableColumn.setCellValueFactory(entry -> entry.getValue().countProperty());
        playerNameTableColumn.setCellValueFactory(entry -> entry.getValue().nameProperty());
        playerPriceTableColumn.setCellValueFactory(entry -> entry.getValue().priceProperty());
        playerSizeTableColumn.setCellValueFactory(entry -> entry.getValue().sizeProperty());
        playerCountTableColumn.setCellValueFactory(entry -> entry.getValue().countProperty());

        marketLabel.setUnderline(true);
        playerLabel.setUnderline(true);
        VBox.setMargin(miscInfoGridPane, INSETS_BASE);
        VBox.setMargin(serdePathTextField, INSETS_TOP);
        VBox.setMargin(serdeButtonHBox, INSETS_BASE);
        VBox.setMargin(marketLabel, INSETS_BASE);
        VBox.setMargin(playerLabel, INSETS_TOP);
        VBox.setMargin(marketInventoryTableView, INSETS_BASE);
        VBox.setMargin(playerInventoryTableView, INSETS_BASE);

        serdeButtonHBox.setSpacing(SPACING);

        AnchorPane.setBottomAnchor(baseHBox, SPACING * 1.5);
        AnchorPane.setLeftAnchor(baseHBox, SPACING);
        AnchorPane.setRightAnchor(baseHBox, SPACING);
        AnchorPane.setTopAnchor(baseHBox, SPACING);

        HBox.setHgrow(leftVBox, Priority.NEVER);
        HBox.setHgrow(rightVBox, Priority.SOMETIMES);
        HBox.setHgrow(serdePathTextField, Priority.ALWAYS);
        HBox.setHgrow(saveButton, Priority.ALWAYS);
        HBox.setHgrow(loadButton, Priority.ALWAYS);
        HBox.setHgrow(exitButton, Priority.ALWAYS);
        VBox.setVgrow(leftSpacerRegion, Priority.ALWAYS);

        serdeButtonHBox.setAlignment(Pos.CENTER);
        saveButton.setAlignment(Pos.CENTER);
        saveButton.setMaxWidth(Double.MAX_VALUE);
        loadButton.setAlignment(Pos.CENTER);
        loadButton.setMaxWidth(Double.MAX_VALUE);
        exitButton.setAlignment(Pos.CENTER);
        exitButton.setMaxWidth(Double.MAX_VALUE);

        serdePathTextField.setPromptText(TAG_TEXTFIELD_SERDEPATH);

        miscInfoGridPane.setHgap(SPACING);
        miscInfoGridPane.setVgap(SPACING);

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

    public void drawMap() {
        marketMapCanvas.setWidth(MAP_SIDELENGTH);
        marketMapCanvas.setHeight(MAP_SIDELENGTH);
        marketMapCanvas.getGraphicsContext2D().clearRect(
                0.0,
                0.0,
                marketMapCanvas.getWidth(),
                marketMapCanvas.getHeight());

        marketMapCanvas.getGraphicsContext2D().strokeLine(
                MAP_COORD_MARKET_EDGE_NEAR,
                MAP_COORD_MARKET_EDGE_NEAR,
                MAP_COORD_MARKET_EDGE_FAR,
                MAP_COORD_MARKET_EDGE_NEAR);
        marketMapCanvas.getGraphicsContext2D().strokeLine(
                MAP_COORD_MARKET_EDGE_NEAR,
                MAP_COORD_MARKET_EDGE_FAR,
                MAP_COORD_MARKET_EDGE_FAR,
                MAP_COORD_MARKET_EDGE_FAR);
        marketMapCanvas.getGraphicsContext2D().strokeLine(
                MAP_COORD_MARKET_EDGE_NEAR,
                MAP_COORD_MARKET_EDGE_NEAR,
                MAP_COORD_MARKET_EDGE_NEAR,
                MAP_COORD_MARKET_EDGE_FAR);
        marketMapCanvas.getGraphicsContext2D().strokeLine(
                MAP_COORD_MARKET_EDGE_FAR,
                MAP_COORD_MARKET_EDGE_NEAR,
                MAP_COORD_MARKET_EDGE_FAR,
                MAP_COORD_MARKET_EDGE_FAR);
        marketMapCanvas.getGraphicsContext2D().strokeLine(
                MAP_COORD_MARKET_EDGE_NEAR,
                MAP_COORD_MARKET_EDGE_NEAR,
                MAP_COORD_MARKET_EDGE_FAR,
                MAP_COORD_MARKET_EDGE_FAR);
        marketMapCanvas.getGraphicsContext2D().strokeLine(
                MAP_COORD_MARKET_EDGE_NEAR,
                MAP_COORD_MARKET_EDGE_FAR,
                MAP_COORD_MARKET_EDGE_FAR,
                MAP_COORD_MARKET_EDGE_NEAR);

        marketMapCanvas.getGraphicsContext2D().setFill(MAP_MARKETCOLOR_BASE);
        if (gameState.get() != null && gameState.get().getCurrentMarket().getValue() == marketNW)
            marketMapCanvas.getGraphicsContext2D().setFill(MAP_MARKETCOLOR_HIGHLIGHT);
        marketMapCanvas.getGraphicsContext2D().fillOval(
                MAP_COORD_MARKET_NEAR,
                MAP_COORD_MARKET_NEAR,
                MAP_MARKETDIAMETER, MAP_MARKETDIAMETER);
        marketMapCanvas.getGraphicsContext2D().setFill(MAP_MARKETCOLOR_BASE);
        if (gameState.get() != null && gameState.get().getCurrentMarket().getValue() == marketNE)
            marketMapCanvas.getGraphicsContext2D().setFill(MAP_MARKETCOLOR_HIGHLIGHT);
        marketMapCanvas.getGraphicsContext2D().fillOval(
                MAP_COORD_MARKET_FAR,
                MAP_COORD_MARKET_NEAR,
                MAP_MARKETDIAMETER, MAP_MARKETDIAMETER);
        marketMapCanvas.getGraphicsContext2D().setFill(MAP_MARKETCOLOR_BASE);
        if (gameState.get() != null && gameState.get().getCurrentMarket().getValue() == marketSW)
            marketMapCanvas.getGraphicsContext2D().setFill(MAP_MARKETCOLOR_HIGHLIGHT);
        marketMapCanvas.getGraphicsContext2D().fillOval(
                MAP_COORD_MARKET_NEAR,
                MAP_COORD_MARKET_FAR,
                MAP_MARKETDIAMETER, MAP_MARKETDIAMETER);
        marketMapCanvas.getGraphicsContext2D().setFill(MAP_MARKETCOLOR_BASE);
        if (gameState.get() != null && gameState.get().getCurrentMarket().getValue() == marketSE)
            marketMapCanvas.getGraphicsContext2D().setFill(MAP_MARKETCOLOR_HIGHLIGHT);
        marketMapCanvas.getGraphicsContext2D().fillOval(
                MAP_COORD_MARKET_FAR,
                MAP_COORD_MARKET_FAR,
                MAP_MARKETDIAMETER, MAP_MARKETDIAMETER);

        marketMapCanvas.getGraphicsContext2D().strokeOval(
                MAP_COORD_MARKET_NEAR,
                MAP_COORD_MARKET_NEAR,
                MAP_MARKETDIAMETER, MAP_MARKETDIAMETER);
        marketMapCanvas.getGraphicsContext2D().strokeOval(
                MAP_COORD_MARKET_FAR,
                MAP_COORD_MARKET_NEAR,
                MAP_MARKETDIAMETER, MAP_MARKETDIAMETER);
        marketMapCanvas.getGraphicsContext2D().strokeOval(
                MAP_COORD_MARKET_NEAR,
                MAP_COORD_MARKET_FAR,
                MAP_MARKETDIAMETER, MAP_MARKETDIAMETER);
        marketMapCanvas.getGraphicsContext2D().strokeOval(
                MAP_COORD_MARKET_FAR,
                MAP_COORD_MARKET_FAR,
                MAP_MARKETDIAMETER, MAP_MARKETDIAMETER);
    }
}
