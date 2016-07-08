package Graphics;

import Logic.Map.Sector;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * Created by mostm on 07/07/2016.
 */

/**
 * these are the cells of our map in the game which show the enemies, towers, path, etc.
 */
public class Cell extends Rectangle {
    private int x;
    private int y;
    private Sector sector;

    public Cell(double width, double height, int x, int y, Sector sector){
        super(width, height);
        this.x = x;
        this.y = y;
        this.sector = sector;
    }

    public int getXCoordination() {
        return x;
    }

    public int getYCoordination() {
        return y;
    }

    public Sector getSector() {
        return sector;
    }
}
