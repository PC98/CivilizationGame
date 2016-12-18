package view;

import controller.GameController;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;

/**
 * This class represents the GameScreen class
 */
public class GameScreen extends BorderPane {

    private static GameScreen instance;
    private static ResourcesMenu rMenu = new ResourcesMenu();
    /**
     * Creates a new view into the game. this layout should include
     * the rescource bar, grid map, and action menus
     *
     */
    public GameScreen() {
        GridFX gridfx = GridFX.getInstance();
        setRight(gridfx);
        setMargin(getRight(), new Insets(25, 20, 15, 0));

        setTop(getResources().getRootNode());
        setMargin(getTop(), new Insets(25, 0, 0, 550));
        instance = this;
    }

    public static GameScreen getInstance() {
        return instance;
    }
    /**
     * This method should update the gridfx and the resource bar
     */
    public void update() {
        rMenu.update();
        GridFX.update();
    }
    /**
    * this method should return the resource menu
    * @return resource menu
    */
    public static ResourcesMenu getResources() {
        return rMenu;
    }
    /**
     * This method switches menus based on passed in game state.
     * Game.java calls this to selectively control which menus are displayed
     * @param state
     */
    public static void switchMenu(GameController.GameState state) {
        AbstractMenu obj;
        switch (state) {
        case NEUTRAL:
            obj = new StatusMenu();
            break;
        case MILITARY:
            obj = new MilitaryMenu();
            break;
        case WORKER:
            obj = new WorkerMenu();
            break;
        case BUILDING:
            obj = new BuildingMenu();
            break;
        case RECRUITING:
            obj = new RecruitMenu();
            break;
        default:
            obj = new AbstractMenu();
        }
        instance.setLeft(obj.getRootNode());
        setMargin(instance.getLeft(), new Insets(0, 0, 0, 15));
    }
}
