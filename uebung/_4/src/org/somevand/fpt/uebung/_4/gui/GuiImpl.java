package org.somevand.fpt.uebung._4.gui;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.somevand.fpt.uebung._4.data.Ware;

public class GuiImpl extends Application implements GUI {

    private static final double SPACING = 10.0;
    private static final String TAG_BALANCE                 = "Balance:";
    private static final String TAG_CARGOSPACE              = "Available Cargo Space:";
    private static final String TAG_WINBALANCE              = "Balance needed to retire:";
    private static final String TAG_TEXTFIELD_SERDEPATH     = "Save/Load Path ...";
    private static final String TAG_BUTTON_SAVE             = "Save";
    private static final String TAG_BUTTON_LOAD             = "Load";
    private static final String TAG_BUTTON_EXIT             = "Exit";
    private static final String TAG_COLUMN_WARENAME         = "Ware";
    private static final String TAG_COLUMN_PRICE            = "Price per Unit";
    private static final String TAG_COLUMN_SIZE             = "Size";
    private static final String TAG_COLUMN_COUNT            = "Count Available";
    private static final String TAG_MARKETINVENTORY         = "Market Inventory";
    private static final String TAG_PLAYERINVENTORY         = "Player Inventory";

    private static GuiImpl instance = null;

    private Stage primaryStage = null;
    private Scene primaryScene = null;

    private AnchorPane baseAnchorPane                                           = new AnchorPane();
        private HBox baseHBox                                                       = new HBox(SPACING);
            private VBox leftVBox                                                       = new VBox(SPACING);
                private Canvas marketMapCanvas                                              = new Canvas();
                private GridPane miscInfoGridPane                                           = new GridPane();
                    private Label balanceTagLabel                                               = new Label(TAG_BALANCE);
                    private Label balanceLabel                                                  = new Label();
                    private Label cargoSpaceTagLabel                                            = new Label(TAG_CARGOSPACE);
                    private Label cargoSpaceLabel                                               = new Label();
                    private Label winBalanceTagLabel                                            = new Label(TAG_WINBALANCE);
                    private Label winBalanceLabel                                               = new Label();
                private VBox serdeVBox                                                      = new VBox(SPACING);
                    private TextField serdePathTextField                                        = new TextField();
                    private HBox serdeButtonHBox                                                = new HBox(SPACING);
                        private Button saveButton                                                   = new Button(TAG_BUTTON_SAVE);
                        private Button loadButton                                                   = new Button(TAG_BUTTON_LOAD);
                        private Button exitButton                                                   = new Button(TAG_BUTTON_EXIT);
            private VBox rightVBox                                                      = new VBox(SPACING);
                private Label marketLabel                                                   = new Label(TAG_MARKETINVENTORY);
                private TableView<InventoryEntry> marketInventoryTableView                  = new TableView<>();
                    private TableColumn<InventoryEntry, String> marketNameTableColumn           = new TableColumn<>(TAG_COLUMN_WARENAME);
                    private TableColumn<InventoryEntry, Number> marketPriceTableColumn          = new TableColumn<>(TAG_COLUMN_PRICE);
                    private TableColumn<InventoryEntry, Number> marketSizeTableColumn           = new TableColumn<>(TAG_COLUMN_SIZE);
                    private TableColumn<InventoryEntry, Number> marketCountTableColumn          = new TableColumn<>(TAG_COLUMN_COUNT);
                private Region marketPlayerSpacerRegion                                     = new Region();
                private Label playerLabel                                                   = new Label(TAG_PLAYERINVENTORY);
                private TableView<InventoryEntry> playerInventoryTableView                  = new TableView<>();
                    private TableColumn<InventoryEntry, String> playerNameTableColumn           = new TableColumn<>(TAG_COLUMN_WARENAME);
                    private TableColumn<InventoryEntry, Number> playerPriceTableColumn          = new TableColumn<>(TAG_COLUMN_PRICE);
                    private TableColumn<InventoryEntry, Number> playerSizeTableColumn           = new TableColumn<>(TAG_COLUMN_SIZE);
                    private TableColumn<InventoryEntry, Number> playerCountTableColumn          = new TableColumn<>(TAG_COLUMN_COUNT);

    public static GuiImpl getInstance() {
        return instance;
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
        this.primaryScene = new Scene(baseAnchorPane);
        this.primaryStage.setScene(primaryScene);

        baseAnchorPane.getChildren().add(baseHBox);
            baseHBox.getChildren().add(leftVBox);
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
        baseHBox.getChildren().add(rightVBox);
            rightVBox.getChildren().add(marketLabel);
            rightVBox.getChildren().add(marketInventoryTableView);
                marketInventoryTableView.getColumns().add(marketNameTableColumn);
                marketInventoryTableView.getColumns().add(marketPriceTableColumn);
                marketInventoryTableView.getColumns().add(marketSizeTableColumn);
                marketInventoryTableView.getColumns().add(marketCountTableColumn);
            rightVBox.getChildren().add(marketPlayerSpacerRegion);
            rightVBox.getChildren().add(playerLabel);
            rightVBox.getChildren().add(playerInventoryTableView);
                playerInventoryTableView.getColumns().add(playerNameTableColumn);
                playerInventoryTableView.getColumns().add(playerPriceTableColumn);
                playerInventoryTableView.getColumns().add(playerSizeTableColumn);
                playerInventoryTableView.getColumns().add(playerCountTableColumn);

        AnchorPane.setBottomAnchor(baseHBox, SPACING);
        AnchorPane.setLeftAnchor(baseHBox, SPACING);
        AnchorPane.setRightAnchor(baseHBox, SPACING);
        AnchorPane.setTopAnchor(baseHBox, SPACING);

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


        miscInfoGridPane.setGridLinesVisible(true);

        primaryStage.show();
    }

    @Override
    public void setState(GuiDisplayableGameState state) {
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
}
