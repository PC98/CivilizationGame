package view;


import controller.GameController;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.MapObject;
import model.TerrainTile;
import javafx.scene.effect.DropShadow;
/**
 * Created by RuYiMarone on 11/11/2016.
 */
public class TerrainTileFX extends StackPane {
    private Rectangle overlay;
    private ImageView background;
    private TerrainTile tile;
    private ImageView icon = new ImageView("File:./bologna");
    /**
     * Constructor for TerrainTileFX.
     * Creates a Rectangle for the highlighting and overlay
     * Creates ImageViews for the background terrain and icon
     * Transitions states when a tile is clicked
     * @param tile
     */
    public TerrainTileFX(TerrainTile tile) {
        this.tile = tile;
        overlay = new Rectangle(70, 70, Color.rgb(0, 0, 0, 0.0));
        overlay.setStroke(Color.BLACK);
        this.background = new ImageView(tile.getImage());
        this.getChildren().addAll(background, overlay);
        updateTileView();
        this.setOnMouseEntered(event -> {
                setCursor(Cursor.HAND);
            });
        this.setOnMousePressed(event -> {
                GameController.setLastClicked(this);
            });
    }
    /**
     * gets the TerrainTile of this TerrainTileFX
     * @return TerrainTile
     */
    public TerrainTile getTile() {
        return tile;
    }
    /**
     * this method updates the view of this TerrainTileFX.
     * It should check if the TerrainTile is empty. If it is empty,
     * set the overlay to be transparent. If it is not empty, fill
     * the overlay with the color of the occupant on the terrain tile
     * If the TerrainTileFX contains an icon, remove it. If the tile is
     * not empty, get the image of the occupant of the tile and add the
     *image of the occupant to the tile.
     */
    public void updateTileView() {
        MapObject obj = this.getTile().getOccupant();
        boolean bool = obj != null;
        overlay.setFill(bool ? obj.getColor() : Color.TRANSPARENT);
        if (this.equals(GameController.getLastClicked())) {
            DropShadow borderGlow = new DropShadow();
            borderGlow.setOffsetY(0f);
            borderGlow.setOffsetX(0f);
            borderGlow.setColor(Color.RED);
            borderGlow.setRadius(20);
            borderGlow.setSpread(0.4);
            overlay.setStroke(Color.TRANSPARENT);
            this.setEffect(borderGlow);
        } else {
            this.setEffect(null);
            overlay.setStroke(Color.BLACK);
            overlay.setFill(bool ? obj.getColor() : Color.TRANSPARENT);
        }
        if (this.getChildren().contains(icon)) {
            this.getChildren().remove(icon);
        }
        if (obj != null) {
            this.icon = new ImageView(obj.getImage());
            this.getChildren().add(icon);
        }
    }
}