package edu.westga.cs3211.pirateinventory.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String WINDOW_TITLE = "Pirate Ship Inventory - Login";
    private static final String LOGIN_VIEW = "/edu/westga/cs3211/pirateinventory/view/LoginView.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(LOGIN_VIEW));
        Pane root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle(WINDOW_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
