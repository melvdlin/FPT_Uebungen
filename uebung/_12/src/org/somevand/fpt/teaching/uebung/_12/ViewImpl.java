package org.somevand.fpt.teaching.uebung._12;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ViewImpl extends BorderPane implements View {
    private static class Constants {
        public static final double padding = 10.0;
        public static final double buttonSize = 50.0;
    }

    private Controller controller;

    private final Button num1 = buildButton("1", Constants.buttonSize, Constants.padding, e -> getTextField().appendText("1"));
    private final Button num2 = buildButton("2", Constants.buttonSize, Constants.padding, e -> getTextField().appendText("2"));
    private final Button num3 = buildButton("3", Constants.buttonSize, Constants.padding, e -> getTextField().appendText("3"));
    private final Button num4 = buildButton("4", Constants.buttonSize, Constants.padding, e -> getTextField().appendText("4"));
    private final Button num5 = buildButton("5", Constants.buttonSize, Constants.padding, e -> getTextField().appendText("5"));
    private final Button num6 = buildButton("6", Constants.buttonSize, Constants.padding, e -> getTextField().appendText("6"));
    private final Button num7 = buildButton("7", Constants.buttonSize, Constants.padding, e -> getTextField().appendText("7"));
    private final Button num8 = buildButton("8", Constants.buttonSize, Constants.padding, e -> getTextField().appendText("8"));
    private final Button num9 = buildButton("9", Constants.buttonSize, Constants.padding, e -> getTextField().appendText("9"));
    private final Button num0 = buildButton("0", Constants.buttonSize, Constants.padding, e -> getTextField().appendText("0"));
    private final Button backspace = buildButton("C", Constants.buttonSize, Constants.padding, e -> {
        if (getTextField().getLength() > 0) {
            getTextField().deleteText(getTextField().getLength() - 1, getTextField().getLength());
        }
    });
    private final Button clear = buildButton("CE", Constants.buttonSize, Constants.padding, e -> getTextField().clear());
    private final Button add = buildButton("+", Constants.buttonSize, Constants.padding, e -> {
        getController().submitAdd(getTextField().getText());
        getTextField().clear();
    });
    private final Button subtract = buildButton("-", Constants.buttonSize, Constants.padding, e -> {
        getController().submitSubtract(getTextField().getText());
        getTextField().clear();
    });
    private final Button multiply = buildButton("x", Constants.buttonSize, Constants.padding, e -> {
        getController().submitMultiply(getTextField().getText());
        getTextField().clear();
    });
    private final Button divide = buildButton("/", Constants.buttonSize, Constants.padding, e -> {
        getController().submitDivide(getTextField().getText());
        getTextField().clear();
    });
    private final Button result = buildButton("=", Constants.buttonSize, Constants.padding, e ->
            getController().calculateResult(getTextField().getText()));
    private final TextField textField = new TextField();
    private final GridPane gridPane = new GridPane();
    private final HBox hBox = new HBox();
    private final VBox vBox = new VBox();


    public ViewImpl() {
        super();
        style();
        assemble();

        // filter non-numeric key stroke
        textField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("\\d*")) {
                event.consume();
            }
        });
    }


    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Controller getController() {
        return controller;
    }

    @Override
    public TextField getTextField() {
        return textField;
    }

    private static Button buildButton(String text, double prefSize, double padding, EventHandler<ActionEvent> onAction) {
        Button button = new Button(text);
        button.setMinHeight(prefSize);
        button.setMinWidth(prefSize);
        button.setPadding(new Insets(padding));
        button.setOnAction(onAction);
        return button;
    }

    private void assemble() {
        hBox.getChildren().add(textField);
        vBox.getChildren().addAll(add, subtract, multiply, divide, result);
        gridPane.addRow(0, num7, num8, num9);
        gridPane.addRow(1, num4, num5, num6);
        gridPane.addRow(2, num1, num2, num3);
        gridPane.addRow(3, backspace, num0, clear);

        this.setTop(hBox);
        this.setRight(vBox);
        this.setCenter(gridPane);
    }

    private void style() {
        hBox.setPadding(new Insets(Constants.padding));
        hBox.setSpacing(Constants.padding);

        vBox.setPadding(new Insets(Constants.padding));
        vBox.setSpacing(Constants.padding);

        gridPane.setPadding(new Insets(Constants.padding));
        gridPane.setHgap(Constants.padding);
        gridPane.setVgap(Constants.padding);
    }

}
