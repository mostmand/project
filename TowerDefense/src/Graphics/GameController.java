package Graphics;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by mostm on 30/06/2016.
 */
public class GameController implements Initializable {
    @FXML
    private Button pauseButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane gameGrid;
    @FXML
    private ImageView basicTower;
    @FXML
    private ImageView lightTower;
    @FXML
    private ImageView darkTower;
    @FXML
    private ImageView fireTower;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            pauseButton.setOnAction(event -> System.out.println("Pause Button was clicked!!!"));
            basicTower.setOnMouseClicked(event -> System.out.println("Basic Tower Clicked"));
            lightTower.setOnMouseClicked(event -> System.out.println("Light Tower Clicked"));
            darkTower.setOnMouseClicked(event -> System.out.println("Dark Tower Clicked"));
            fireTower.setOnMouseClicked(event -> System.out.println("Fire Tower Clicked"));
            gridInit();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void gridInit() {
        gameGrid.setVgap(5);
        gameGrid.setHgap(5);
        Rectangle r;
        for (int i = 0; i < 100; i ++){
            for (int j = 0; j < 100; j ++){
                r = new Rectangle(20, 20);
                r.setFill(Color.GREEN);
                gameGrid.add(r, i, j);
            }
//                gameGrid.setGridLinesVisible(true);
        }
    }
}
