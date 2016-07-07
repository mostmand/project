package Graphics;

import Logic.Game;
import Logic.MilitaryForces.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mostm on 30/06/2016.
 */
public class GameController implements Initializable {
    Game game;

    ImagePattern path = new ImagePattern(new Image("/Graphics/images/path.png"));
    ImagePattern grass = new ImagePattern(new Image("/Graphics/images/grass.png"));
    ImagePattern soldier = new ImagePattern(new Image("/Graphics/images/soldier.jpg"));
    ImagePattern tower = new ImagePattern(new Image("/Graphics/images/14955-illustration-of-a-cartoon-castle-tower-with-flag-pv.png"));

    @FXML
    private Button pauseButton;
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


    /*
    overridden method for initializing the JavaFX Application
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            game = new Game();
//            game.setTower(1, 1, MilitaryType.BASIC);
//            game.setTower(15, 5, MilitaryType.DARK);
            game.startGame();
            scheduleTimer();
            gridInit();
            setActions();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    this method sets the actions related to buttons
    this method is not finalized yet and must be changed
     */
    private void setActions() {
        pauseButton.setOnAction(event ->{
            game.gameMap.getSector(2, 10).pathIn = null;
        });
        basicTower.setOnMouseClicked(event -> System.out.println("Basic Tower Clicked"));
        lightTower.setOnMouseClicked(event -> System.out.println("Light Tower Clicked"));
        darkTower.setOnMouseClicked(event -> System.out.println("Dark Tower Clicked"));
        fireTower.setOnMouseClicked(event -> System.out.println("Fire Tower Clicked"));

        ObservableList<Node> list = gameGrid.getChildren();
        for (int i = 0; i < list.size(); i ++){
            Cell cell = (Cell)list.get(i);
            cell.setOnMouseClicked(event -> System.out.println(cell.getXCoordination() + " " + cell.getYCoordination()));
        }
    }

    /*
    this is a method for scheduling the timer for updating the GUI
     */
    private void scheduleTimer() {
        TimerTask gridUpdate = new TimerTask() {
            @Override
            public void run() {
                gridUpdate();
            }
        };
        Timer timer = new Timer();
        timer.schedule(gridUpdate, 0, 300);
    }

    /*
    this is for initializing the game gridPane
     */
    private void gridInit() {
        gameGrid.setVgap(5);
        gameGrid.setHgap(5);
        Cell c;
        for (int i = 0; i < game.gameMap.width; i ++){
            for (int j = 0; j < game.gameMap.height; j ++){
                c = new Cell(40, 40, i, j, game.gameMap.getSector(i + 1, j + 1));
                gameGrid.add(c, c.getXCoordination(), c.getYCoordination());
            }
        }
    }

    /*
    the method which updates the GUI
     */
    public void gridUpdate(){
        Cell c;
        for (int i = 0; i < gameGrid.getChildren().size(); i ++){
            c = (Cell)gameGrid.getChildren().get(i);
            if(c.getSector().pathIn != null){
                c.setFill(path);
            }
            else {
                c.setFill(grass);
            }
            if(c.getSector().isOccupied()){
                for (Military m: c.getSector().occupant) {
                    if(m instanceof Tower){
                        c.setFill(tower);
//                            if(m instanceof BasicEnemy){
//
//                            }
//                            else if(m instanceof DarkEnemy){
//
//                            }
//                            else if(m instanceof FireEnemy){
//
//                            }
//                            else if(m instanceof TreeEnemy){
//
//                            }
//                            else if(m instanceof LightEnemy){
//
//
                    }
                    if(m instanceof Enemy){
                        c.setFill(soldier);
                    }
                }
            }
        }
    }
}
