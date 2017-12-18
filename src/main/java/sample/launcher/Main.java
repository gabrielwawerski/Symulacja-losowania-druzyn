package sample.launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        Parent root = FXMLLoader.load(getClass().getResource("/forms/Scene.fxml"));

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        final double width = primaryScreenBounds.getMinX();
        final double height = primaryScreenBounds.getMinY();

        root.setLayoutX(width);
        root.setLayoutY(height);

        primaryStage.setTitle("Hello World");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
