package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by Tian-Yo Yang on 11/11/2016.
 * This class represents the Start Screen for the Civ applicatios. This is the
 * layout that should be displayed upon running the Civ application.
 *
 * This class should have and display
 * 1. a background
 * 2. a list of Civilizations
 * 3. a Start button
 */
public class StartScreen extends StackPane {

    private ListView<CivEnum> civList;
    private Button start;

    /**
    * constuctor of the start screen. Should set the background
    * image and display a list of civilizations and a start button
    */
    public StartScreen() {
        VBox vbox = new VBox();
        civList = new ListView<>(FXCollections.observableArrayList(
            CivEnum.values()));
        civList.setPrefHeight(3 * 24 + 5);
        civList.setMaxWidth(200.0);

        start = new Button("Start");
        start.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        start.setPrefWidth(80);

        Label l = new Label("Select a Civilization to Begin");
        l.setTextFill(Color.RED);
        l.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Image im = new Image("File:./src/main/java/view/civ_background.png"
            , 960, 540, true, true);
        setBackground(new Background(new BackgroundImage(im
            , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT
            , BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        vbox.setSpacing(10);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        vbox.getChildren().addAll(l, civList, start);
        getChildren().add(vbox);
        setMargin(vbox, new Insets(0, 0, 30, 0));
    }
    /**
    * gets the start button
    * @return the start button
    */
    public Button getStartButton() {
        return start;
    }
    /**
    * return a ListView of CivEnums representing the list of
    * available civilizations to choose from
    * @return listview of CivEnum
    */
    public ListView<CivEnum> getCivList() {
        return civList;
    }
    /**
    * should set the civilization taken in by the method to the civilization
    * selected for this screen
    */
}