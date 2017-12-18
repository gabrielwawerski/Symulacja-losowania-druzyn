package sample.launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.controllers.SceneController;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        final double WIDTH = primaryScreenBounds.getMinX();
        final double HEIGHT = primaryScreenBounds.getMinY();

        Parent root = FXMLLoader.load(getClass().getResource("/forms/Scene.fxml"));
        root.setLayoutX(WIDTH);
        root.setLayoutY(HEIGHT);

        primaryStage.setTitle("Wylosuj grupy na Mundial!");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}