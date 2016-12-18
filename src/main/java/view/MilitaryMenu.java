package view;

import controller.GameController;
import javafx.scene.control.Button;

/**
 * Created by William on 11/11/2016.
 */

public class MilitaryMenu extends AbstractMenu {
    /**
    * Implement the buttons and actions associated with
    * the buttons for the military menu
    */

    public MilitaryMenu() {
        Button attack = new Button("Attack");
        attack.setOnMousePressed(e -> {
                GameController.attacking();
                GameScreen.getInstance().update();
            });
        Button move = new Button("Move");
        move.setOnMousePressed(e -> {
                GameController.moving();
                GameScreen.getInstance().update();
            });
        super.addMenuItem(attack);
        super.addMenuItem(move);
    }
}
