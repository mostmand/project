package Graphics;

import Logic.Exceptions.InsufficientBalanceException;
import Logic.Exceptions.InvalidCoordinatesException;
import Logic.Game;
import Logic.MilitaryForces.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mostm on 30/06/2016.
 */
public class GameController implements Initializable {
    public Game game;
    private Stage stage;
    TimerTask gridUpdate;

    HashMap<String, ImagePattern> images;

    MilitaryType tempMilitaryType;

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
    @FXML
    private ImageView treeTower;
    @FXML
    private Label moneyLabel;


    public void setStage(Stage stage){
        this.stage = stage;
    }

    /*
    overridden method for initializing the JavaFX Application
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            setActions();
            loadImages();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    this method loads the images to a HashMap
     */
    private void loadImages() {
        images = new HashMap<String, ImagePattern>();
        images.put("treeTower", new ImagePattern(new Image("Graphics/Images/treeTower.jpg")));
        images.put("lightTower", new ImagePattern(new Image("Graphics/Images/lightTower.png")));
        images.put("darkTower", new ImagePattern(new Image("Graphics/Images/darkTower.jpg")));
        images.put("basicTower", new ImagePattern(new Image("Graphics/Images/basicTower.jpg")));
        images.put("fireTower", new ImagePattern(new Image("Graphics/Images/fireTower.jpg")));

        images.put("treeEnemy", new ImagePattern(new Image("Graphics/Images/treeEnemy.jpg")));
        images.put("lightEnemy", new ImagePattern(new Image("Graphics/Images/lightEnemy.jpg")));
        images.put("darkEnemy", new ImagePattern(new Image("Graphics/Images/darkEnemy.jpg")));
        images.put("basicEnemy", new ImagePattern(new Image("Graphics/Images/basicEnemy.jpg")));
        images.put("fireEnemy", new ImagePattern(new Image("Graphics/Images/fireEnemy.jpg")));

        images.put("grass", new ImagePattern(new Image("Graphics/Images/grass.png")));
        images.put("path", new ImagePattern(new Image("Graphics/Images/path.png")));
    }

    /*
    this method sets the actions related to buttons
    this method is not finalized yet and must be changed
     */
    public void setActions() {
        pauseButton.setOnAction(event ->{
            FXMLLoader pauseMenuLoader = new FXMLLoader();
            pauseMenuLoader.setLocation(Main.class.getResource("/Graphics/pauseMenu.fxml"));
            try {
                game.pauseGame();
                AnchorPane pauseMenu = pauseMenuLoader.load();
                Scene pauseMenuScene = new Scene(pauseMenu);
                PauseMenuController pauseMenuController = (PauseMenuController)pauseMenuLoader.getController();
                pauseMenuScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
                pauseMenuController.setStage(stage);
                pauseMenuController.setGame(game);
                stage.setScene(pauseMenuScene);
                stage.show();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
        basicTower.setOnMouseClicked(event -> tempMilitaryType = MilitaryType.BASIC);
        lightTower.setOnMouseClicked(event -> tempMilitaryType = MilitaryType.LIGHT);
        darkTower.setOnMouseClicked(event -> tempMilitaryType = MilitaryType.DARK);
        fireTower.setOnMouseClicked(event -> tempMilitaryType = MilitaryType.FIRE);
        treeTower.setOnMouseClicked(event -> tempMilitaryType = MilitaryType.TREE);

        ObservableList<Node> list = gameGrid.getChildren();
        for (int i = 0; i < list.size(); i ++){
                Cell cell = (Cell)list.get(i);
                cell.setOnMouseClicked(event -> {
                    try {
                        if(tempMilitaryType != null)
                            game.setTower(cell.getXCoordination() + 1, cell.getYCoordination() + 1, tempMilitaryType);
                        tempMilitaryType = null;
                    }
                    catch (InsufficientBalanceException e){
                        System.out.println("Your Balance is insufficient");
                    }
                    catch (InvalidCoordinatesException e){
                        System.out.println("You cannot put your tower here");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                });
        }
    }

    /*
    this is a method for scheduling the timer for updating the GUI
     */
    public void scheduleTimer() {
        gridUpdate = new TimerTask() {
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
    public void gridInit() {
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
        //the code inside the run later is for updating player's money shown in the moneyLabel
        Platform.runLater(new Runnable() {
            public void run() {
                moneyLabel.setText("Your Money:" + game.getPlayerBalance());
            }
        });

        for (int i = 0; i < gameGrid.getChildren().size(); i ++){
            c = (Cell)gameGrid.getChildren().get(i);
            if(c.getSector().pathIn != null){
                c.setFill(images.get("path"));
            }
            else {
                c.setFill(images.get("grass"));
            }
            if(c.getSector().isOccupied()){
                for (Military m: c.getSector().occupant) {
                    if(m instanceof Tower){
                            if(m instanceof BasicTower){
                                c.setFill(images.get("basicTower"));
                            }
                            else if(m instanceof DarkTower){
                                c.setFill(images.get("darkTower"));
                            }
                            else if(m instanceof FireTower){
                                c.setFill(images.get("fireTower"));
                            }
                            else if(m instanceof TreeTower){
                                c.setFill(images.get("treeTower"));
                            }
                            else if(m instanceof LightTower){
                                c.setFill(images.get("lightTower"));
                            }
                    }
                    if(m instanceof Enemy){
                        if(m instanceof BasicEnemy){
                            c.setFill(images.get("basicEnemy"));
                        }
                        else if(m instanceof DarkEnemy){
                            c.setFill(images.get("darkEnemy"));
                        }
                        else if(m instanceof FireEnemy){
                            c.setFill(images.get("fireEnemy"));
                        }
                        else if(m instanceof TreeEnemy){
                            c.setFill(images.get("treeEnemy"));
                        }
                        else if(m instanceof LightEnemy){
                            c.setFill(images.get("lightEnemy"));
                        }
                    }
                }
            }
        }
    }
}
