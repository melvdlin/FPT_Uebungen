package org.somevand.fpt.uebung._4.gui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.somevand.fpt.uebung._4.data.Ware;

import java.util.Map;

public class GuiImpl extends Application implements GUI {

    private enum MappedMarket {
        MM_NONE,
        MM_NW,
        MM_NE,
        MM_SE,
        MM_SW,
    }

    //region Constants
    private static final double INITIAL_HEIGHT = 720;
    private static final double INITIAL_WIDTH = 1280;
    private static final double INITIAL_DIVIDER_POS = 1.0 / 5;
    private static final double SPACING = 10.0;
    private static final Color  MAP_MARKETCOLOR_BASE            = Color.GREY;
    private static final Color  MAP_MARKETCOLOR_HIGHLIGHT       = Color.GREEN;
    private static final double MAP_BORDERWIDTH                 = 5;
    private static final double MAP_INNERSIDELENGTH             = 500;
    private static final double MAP_SIDELENGTH                  = MAP_INNERSIDELENGTH + 2 * MAP_BORDERWIDTH;
    private static final double MAP_MARKETDIAMETER              = MAP_INNERSIDELENGTH / 4;
    private static final double MAP_COORD_MARKET_NEAR           = MAP_BORDERWIDTH;
    private static final double MAP_COORD_MARKET_FAR            = MAP_SIDELENGTH - MAP_BORDERWIDTH - MAP_MARKETDIAMETER;
    private static final double MAP_COORD_MARKET_EDGE_NEAR      = MAP_MARKETDIAMETER / 2;
    private static final double MAP_COORD_MARKET_EDGE_FAR       = MAP_SIDELENGTH - MAP_COORD_MARKET_EDGE_NEAR;
    private static final String TAG_BALANCE                     = "Balance:";
    private static final String TAG_CARGOSPACE                  = "Available cargo space:";
    private static final String TAG_WINBALANCE                  = "Balance needed to retire:";
    private static final String TAG_TEXTFIELD_SERDEPATH         = "Save/Load Path ...";
    private static final String TAG_BUTTON_SAVE                 = "Save";
    private static final String TAG_BUTTON_LOAD                 = "Load";
    private static final String TAG_BUTTON_EXIT                 = "Exit";
    private static final String TAG_COLUMN_WARENAME             = "Ware";
    private static final String TAG_COLUMN_PRICE                = "Price/Unit";
    private static final String TAG_COLUMN_SIZE                 = "Size";
    private static final String TAG_COLUMN_COUNT                = "Count Available";
    private static final String TAG_MARKETINVENTORY             = "Market Inventory";
    private static final String TAG_PLAYERINVENTORY             = "Player Inventory";
    //endregion

    private static GuiImpl instance = null;

    private static GuiDisplayableGameState gameState = null;

    private Stage primaryStage = null;
    private Scene primaryScene = new Scene(new Region(), INITIAL_WIDTH, INITIAL_HEIGHT);

    //region Nodes
    private final AnchorPane baseAnchorPane                                                 = new AnchorPane();
        private final SplitPane baseSplitPane                                               = new SplitPane();
            private final VBox leftVBox                                                     = new VBox(SPACING);
                private final Canvas marketMapCanvas                                        = new Canvas();
                private final GridPane miscInfoGridPane                                     = new GridPane();
                    private final Label balanceTagLabel                                     = new Label(TAG_BALANCE);
                    private final Label balanceLabel                                        = new Label();
                    private final Label cargoSpaceTagLabel                                  = new Label(TAG_CARGOSPACE);
                    private final Label cargoSpaceLabel                                     = new Label();
                    private final Label winBalanceTagLabel                                  = new Label(TAG_WINBALANCE);
                    private final Label winBalanceLabel                                     = new Label();
                private final VBox serdeVBox                                                = new VBox(SPACING);
                    private final TextField serdePathTextField                              = new TextField();
                    private final HBox serdeButtonHBox                                      = new HBox(SPACING);
                        private final Button saveButton                                     = new Button(TAG_BUTTON_SAVE);
                        private final Button loadButton                                     = new Button(TAG_BUTTON_LOAD);
                        private final Button exitButton                                     = new Button(TAG_BUTTON_EXIT);
            private final VBox rightVBox                                                    = new VBox(SPACING);
                private final Label marketLabel                                             = new Label(TAG_MARKETINVENTORY);
                private final TableView<InventoryEntry> marketInventoryTableView            = new TableView<>();
                    private final TableColumn<InventoryEntry, String> nameTableColumn       = new TableColumn<>(TAG_COLUMN_WARENAME);
                    private final TableColumn<InventoryEntry, Number> priceTableColumn      = new TableColumn<>(TAG_COLUMN_PRICE);
                    private final TableColumn<InventoryEntry, Number> sizeTableColumn       = new TableColumn<>(TAG_COLUMN_SIZE);
                    private final TableColumn<InventoryEntry, Number> countTableColumn      = new TableColumn<>(TAG_COLUMN_COUNT);
                private final Region marketPlayerSpacerRegion                               = new Region();
                private final Label playerLabel                                             = new Label(TAG_PLAYERINVENTORY);
                private final TableView<InventoryEntry> playerInventoryTableView            = new TableView<>();
    //endregion

    //region Displayed Data
    private ObservableList<InventoryEntry> playerInventoryList;
    private ObservableList<InventoryEntry> marketInventoryList;
    private Map<Ware, InventoryEntry> playerInventoryEntryMap;
    private Map<Ware, InventoryEntry> marketInventoryEntryMap;
    //endregion

    public static GuiImpl getInstance() {
        return instance;
    }

    public static GUI launch(GuiDisplayableGameState state) {
        Application.launch();
        getInstance().setState(state);
        return getInstance();
    }

    public static GUI launch() {
        return launch((GuiDisplayableGameState) null);
    }

    @Override
    public void init() {
        instance = this;
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
        drawMap(MappedMarket.MM_NW);

        miscInfoGridPane.setGridLinesVisible(true);

        primaryStage.show();
    }

    public void setState(GuiDisplayableGameState state) {
        if (state == gameState) return;
        gameState = state;
        drawMap(MappedMarket.MM_NONE);
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
        // TODO: implement
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

    private void buildUI() {
        baseAnchorPane.getChildren().add(baseSplitPane);
        baseSplitPane.getItems().add(leftVBox);
        leftVBox.getChildren().add(marketMapCanvas);
        leftVBox.getChildren().add(miscInfoGridPane);
        miscInfoGridPane.add(balanceTagLabel, 0, 0);
        miscInfoGridPane.add(balanceLabel, 1, 0);
        miscInfoGridPane.add(cargoSpaceTagLabel, 0, 1);
        miscInfoGridPane.add(cargoSpaceLabel, 1, 1);
        miscInfoGridPane.add(winBalanceTagLabel, 0, 2);
        miscInfoGridPane.add(winBalanceLabel, 1, 2);
        leftVBox.getChildren().add(serdeVBox);
        serdeVBox.getChildren().add(serdePathTextField);
        serdeVBox.getChildren().add(serdeButtonHBox);
        serdeButtonHBox.getChildren().add(saveButton);
        serdeButtonHBox.getChildren().add(loadButton);
        serdeButtonHBox.getChildren().add(exitButton);
//        baseHBox.getChildren().add(rightVBox);
        baseSplitPane.getItems().add(rightVBox);
        rightVBox.getChildren().add(marketLabel);
        rightVBox.getChildren().add(marketInventoryTableView);
        marketInventoryTableView.getColumns().add(nameTableColumn);
        marketInventoryTableView.getColumns().add(priceTableColumn);
        marketInventoryTableView.getColumns().add(sizeTableColumn);
        marketInventoryTableView.getColumns().add(countTableColumn);
        rightVBox.getChildren().add(marketPlayerSpacerRegion);
        rightVBox.getChildren().add(playerLabel);
        rightVBox.getChildren().add(playerInventoryTableView);
        playerInventoryTableView.getColumns().add(nameTableColumn);
        playerInventoryTableView.getColumns().add(priceTableColumn);
        playerInventoryTableView.getColumns().add(sizeTableColumn);
        playerInventoryTableView.getColumns().add(countTableColumn);

        AnchorPane.setBottomAnchor(baseSplitPane, SPACING);
        AnchorPane.setLeftAnchor(baseSplitPane, SPACING);
        AnchorPane.setRightAnchor(baseSplitPane, SPACING);
        AnchorPane.setTopAnchor(baseSplitPane, SPACING);

        baseSplitPane.setOrientation(Orientation.HORIZONTAL);
        baseSplitPane.setDividerPosition(0, INITIAL_DIVIDER_POS);

        HBox.setHgrow(leftVBox, Priority.SOMETIMES);
        HBox.setHgrow(rightVBox, Priority.SOMETIMES);
        HBox.setHgrow(serdePathTextField, Priority.ALWAYS);
        HBox.setHgrow(saveButton, Priority.ALWAYS);
        HBox.setHgrow(loadButton, Priority.ALWAYS);
        HBox.setHgrow(exitButton, Priority.ALWAYS);

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
                winBalanceTagLabel,
                0,
                2,
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
                winBalanceLabel,
                1,
                2,
                1,
                1,
                HPos.RIGHT,
                VPos.CENTER,
                Priority.ALWAYS,
                Priority.NEVER);
    }

    public void drawMap(MappedMarket highlight) {
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
        if (highlight == MappedMarket.MM_NW)
            marketMapCanvas.getGraphicsContext2D().setFill(MAP_MARKETCOLOR_HIGHLIGHT);
        marketMapCanvas.getGraphicsContext2D().fillOval(
                MAP_COORD_MARKET_NEAR,
                MAP_COORD_MARKET_NEAR,
                MAP_MARKETDIAMETER, MAP_MARKETDIAMETER);
        marketMapCanvas.getGraphicsContext2D().setFill(MAP_MARKETCOLOR_BASE);
        if (highlight == MappedMarket.MM_NE)
            marketMapCanvas.getGraphicsContext2D().setFill(MAP_MARKETCOLOR_HIGHLIGHT);
        marketMapCanvas.getGraphicsContext2D().fillOval(
                MAP_COORD_MARKET_FAR,
                MAP_COORD_MARKET_NEAR,
                MAP_MARKETDIAMETER, MAP_MARKETDIAMETER);
        marketMapCanvas.getGraphicsContext2D().setFill(MAP_MARKETCOLOR_BASE);
        if (highlight == MappedMarket.MM_SW)
            marketMapCanvas.getGraphicsContext2D().setFill(MAP_MARKETCOLOR_HIGHLIGHT);
        marketMapCanvas.getGraphicsContext2D().fillOval(
                MAP_COORD_MARKET_NEAR,
                MAP_COORD_MARKET_FAR,
                MAP_MARKETDIAMETER, MAP_MARKETDIAMETER);
        marketMapCanvas.getGraphicsContext2D().setFill(MAP_MARKETCOLOR_BASE);
        if (highlight == MappedMarket.MM_SE)
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
