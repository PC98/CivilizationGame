package runner;

import controller.GameController;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import model.Civilization;
import model.Bandit;
import model.Egypt;
import model.QinDynasty;
import model.RomanEmpire;
import model.Map;
import view.GameScreen;
import view.StartScreen;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.Optional;

/**
 * Created by Tian-Yo Yang on 11/11/2016.
 */
public class CivilizationGame extends Application {
    private StartScreen ss = new StartScreen();
    private static Stage window;
    private MediaPlayer mediaPlayer;
    /**
     * this method is called upon running/launching the application
     * this method should display a scene on the stage
     */
    @Override
    public void start(Stage primaryStage) {
        String musicFile = "src/main/java/Audio/opening.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

        primaryStage.setScene(new Scene(ss, 960, 540));
        primaryStage.show();
        window = primaryStage;
        startGame();
    }

    /**
     * Allows Alert and TextInputDialog to access stage and get
     * displayed in full screen
     * @return Stage
     */
    public static Stage getStage() {
        return window;
    }
    /**
     * This is the main method that launches the javafx application
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method is responsible for setting the scene to the corresponding
     * layout.
     * and returning the scene.
     *
     * @return Scene
     */

    public Scene startGame() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("A New Settlement");
        dialog.setHeaderText("You have built a Settlement!");
        dialog.setContentText("Enter the name of your first town");
        dialog.initOwner(window);

        Button tmp = ss.getStartButton();
        tmp.setOnMouseEntered(event -> {
                tmp.setCursor(Cursor.HAND);
            });
        tmp.setOnMouseClicked(event -> {
                String choice = ss.getCivList().getSelectionModel().
                    getSelectedItems().get(0).toString();
                Civilization civ = null;
                switch (choice) {
                case "Ancient Egypt":
                    civ = new Egypt();
                    break;
                case "Roman Empire":
                    civ = new RomanEmpire();
                    break;
                case "Qin Dynasty":
                    civ = new QinDynasty();
                    break;
                default:
                    System.exit(0);
                }
                GameController.setCivilization(civ);
                GameScreen gs = new GameScreen();
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    Map.putSettlement(result.get(), civ, 0, 9);
                    Map.addEnemies(new Bandit(), 1);
                    GameScreen.getInstance().update();
                    mediaPlayer.stop();
                    window.setScene(new Scene(gs));
                    window.setFullScreen(true);
                }
            });
        return null;
    }
}
