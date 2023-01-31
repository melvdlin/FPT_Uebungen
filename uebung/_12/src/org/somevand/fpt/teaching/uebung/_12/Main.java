package org.somevand.fpt.teaching.uebung._12;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(Main.class);
    }


    @Override
    public void start(Stage primaryStage) {
        Model model = new ModelImpl();
        ViewImpl view = new ViewImpl();
        Controller controller = new ControllerImpl(model, view);
        view.setController(controller);

        primaryStage.setScene(new Scene(view));
        primaryStage.setResizable(false);

        primaryStage.show();
    }
}
