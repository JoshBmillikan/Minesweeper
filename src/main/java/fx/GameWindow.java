package fx;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import minesweeper.Main;

public class GameWindow {
    @FXML private ScrollPane Pane;
    @FXML private GridPane grid;

    @FXML private void initialize() {
        for(int x=0;x<Main.game.getSettings().getSizeX();x++) {
            var constraints = new ColumnConstraints();
            constraints.setFillWidth(true);
            grid.getColumnConstraints().add(constraints);
            for(int y=0;y<Main.game.getSettings().getSizeY();y++) {
                var rConstraints = new RowConstraints();
                rConstraints.setFillHeight(true);
                grid.getRowConstraints().add(rConstraints);
                grid.add(getTileNode(x, y), x, y);
            }
        }
    }

    private static Node getTileNode(final int x, final int y) {
        return null; //todo
    }
}
