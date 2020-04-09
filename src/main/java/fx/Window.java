package fx;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import minesweeper.GameData;
import minesweeper.GameSettings;
import minesweeper.Main;

import java.io.IOException;

public class Window {
    @FXML private Spinner<Integer> height;
    @FXML private Spinner<Integer> width;
    @FXML private ChoiceBox<GameSettings.Difficulty> difficulty;
    @FXML private TextField seedBox;

    @FXML private void initialize() {
        var factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
        factory.setWrapAround(true);
        factory.setValue(4);
        height.setValueFactory(factory);
        width.setValueFactory(factory);
        difficulty.setItems(FXCollections.observableArrayList(GameSettings.Difficulty.values()));
        difficulty.getSelectionModel().select(GameSettings.Difficulty.NORMAL);
    }

    @FXML private void open() {
        final var h = height.getValue();
        final var w = width.getValue();
        final var diff = difficulty.getValue();
        var settings = new GameSettings(h,w,diff);
        final var seed = seedBox.getText().trim();
        Main.game = new GameData(settings,seed.length() > 1 ? seed : null);
        Main.game.Generate();

        var stage = (Stage) height.getScene().getWindow();
        stage.close();
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/fx/GameWindow.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle(String.format("Mine Sweeper(%d X %d)",w,h));
            stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
