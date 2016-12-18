package view;

import controller.GameController;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import model.Building;
import model.MapObject;
import model.Settlement;
import model.TerrainTile;
import runner.CivilizationGame;

/**
 * This class should represents the bulding menu
 */
public class BuildingMenu extends AbstractMenu {
    /**
    * there should be an invest and demolish button for this menu
    * as well as functions associated with the buttons
    */
    public BuildingMenu() {
        Button invest = new Button("Invest");
        TerrainTile tt = GameController.getLastClicked().getTile();
        MapObject mo = tt.getOccupant();
        invest.setOnMouseClicked(e -> {
                if (GameController.getCivilization().getTreasury()
                        .getCoins() >= 25) {
                    ((Building) mo).invest();
                    GameController.getCivilization().getTreasury().spend(25);
                    GameScreen.getInstance().update();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Congratulations!");
                    alert.setHeaderText("Invested");
                    alert.setContentText("You have successfully invested.");
                    alert.initOwner(CivilizationGame.getStage());
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText("Can't Invest");
                    alert.setContentText("You cannot invest on the selected"
                        + " tile because you don't have enough gold.");
                    alert.initOwner(CivilizationGame.getStage());
                    alert.showAndWait();
                }
            });
        Button demolish = new Button("Demolish");
        demolish.setOnMousePressed(e -> {
                if ((!(mo instanceof Settlement)
                    || GameController.getCivilization()
                    .getNumSettlements() > 1)
                    && mo.getOwner() == GameController
                    .getCivilization()) {
                    ((Building) mo).demolish();
                    tt.setOccupant(null);
                    if (mo instanceof Settlement) {
                        GameController.getCivilization()
                            .decrementNumSettlements();
                    }
                    GameController.setLastClicked(null);
                    GameScreen.getInstance().update();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText("Can't Demolish");
                    alert.setContentText("You cannot demolish because you"
                        + " either have one or less settlements, or you do not"
                        + " own this settlement, or the tile does not contain"
                        + " a settlement.");
                    alert.initOwner(CivilizationGame.getStage());
                    alert.showAndWait();
                }
            });
        super.addMenuItem(invest);
        super.addMenuItem(demolish);
    }
}
