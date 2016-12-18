package view;

import controller.GameController;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import model.Convertable;
import model.TerrainTile;
import runner.CivilizationGame;

/**
 * Created by RuYiMarone on 11/11/2016.
 */
public class WorkerMenu extends AbstractMenu {
    /**
    * There should be a convert and move button
    * as well as the functions associated with those
    * buttons
    */
    public WorkerMenu() {
        Button convert = new Button("Convert");
        convert.setOnMousePressed(e -> {
                TerrainTileFX ttfx = GameController.getLastClicked();
                TerrainTile tt = ttfx.getTile();

                if (!((Convertable) tt.getOccupant()).canConvert(tt
                        .getType())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText("Can't Convert");
                    alert.setContentText("You cannot convert on the selected"
                        + " tile.");
                    alert.initOwner(CivilizationGame.getStage());
                    alert.showAndWait();
                } else {
                    tt.setOccupant(((Convertable) tt.getOccupant()).convert());
                    GameScreen.getInstance().update();
                    GameController.setLastClicked(null);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Congratulations!");
                    alert.setHeaderText("Converted");
                    alert.setContentText("You converted the selected"
                        + " tile.");
                    alert.initOwner(CivilizationGame.getStage());
                    alert.showAndWait();
                    ttfx.updateTileView();
                }
            });
        Button move = new Button("Move");
        move.setOnMousePressed(e -> {
                GameController.moving();
                GameScreen.getInstance().update();
            });
        super.addMenuItem(convert);
        super.addMenuItem(move);
    }
}
