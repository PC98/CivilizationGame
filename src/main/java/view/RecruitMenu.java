package view;

import controller.GameController;
import javafx.collections.FXCollections;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import model.Civilization;
import model.TerrainTile;
import model.Unit;
import javafx.collections.ObservableList;
import runner.CivilizationGame;

import java.util.Optional;

/**
 * Created by RuYiMarone on 11/11/2016.
 */
public class RecruitMenu extends AbstractMenu {
    /**
    * recuit menu should have a list of worker/units
    * to choose from. There should also be a select button
    * and the function of the button should be implemented
    *here
    */
    public RecruitMenu() {
        ObservableList<String> units = FXCollections.observableArrayList(
                "Melee Unit", "Ranged Unit", "Hybrid Unit", "Siege Unit"
                , "Settlers", "Farmers", "Coal Miners", "Anglers"
                , "Master Builders");
        ListView<String> unitList = new ListView<>(units);
        unitList.setPrefHeight(9 * 24 + 15);
        unitList.setMaxWidth(200.0);
        getRootNode().getChildren().add(unitList);
        unitList.setOnMouseEntered(e -> {
                unitList.setCursor(Cursor.HAND);
            });
        unitList.setOnMousePressed(e -> {
                String choice = unitList.getSelectionModel()
                    .getSelectedItems().get(0);
                TerrainTileFX ttfx = GameController.getLastClicked();
                TerrainTile tt = ttfx.getTile();
                Civilization civ = GameController.getCivilization();
                Unit myUnit = null;
                switch (choice) {
                case "Melee Unit":
                    myUnit = civ.getMeleeUnit();
                    break;
                case "Ranged Unit":
                    myUnit = civ.getRangedUnit();
                    break;
                case "Hybrid Unit":
                    myUnit = civ.getHybridUnit();
                    break;
                case "Siege Unit":
                    myUnit = civ.getSiegeUnit();
                    break;
                case "Settlers":
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Recruit Settler Unit");
                    dialog.setHeaderText("Name of Settlement Required");
                    dialog.setContentText("Enter name of settlement that"
                        + " this unit will build.");
                    dialog.initOwner(CivilizationGame.getStage());
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        myUnit = civ.getSettlerUnit(result.get());
                    }
                    break;
                case "Farmers":
                    myUnit = civ.getFarmerUnit();
                    break;
                case "Coal Miners":
                    myUnit = civ.getCoalMinerUnit();
                    break;
                case "Anglers":
                    myUnit = civ.getAnglerUnit();
                    break;
                case "Master Builders":
                    myUnit = civ.getMasterBuilderUnit();
                    break;
                default:
                    break;
                }
                if (myUnit == null || !myUnit.isAffordable()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText("Can't Recruit");
                    alert.setContentText("You cannot afford to recruit this"
                        + " unit.");
                    alert.initOwner(CivilizationGame.getStage());
                    alert.showAndWait();
                } else {
                    tt.setOccupant(myUnit);
                    ttfx.updateTileView();
                    myUnit.applyInitialCosts();
                    GameController.setLastClicked(null);
                    GameScreen.getInstance().update();
                }
            });
    }
}
