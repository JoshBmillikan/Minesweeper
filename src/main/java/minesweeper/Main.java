package minesweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static GameData game;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/fx/Window.fxml"));
        var scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mine Sweeper");
        primaryStage.show();
    }
}
