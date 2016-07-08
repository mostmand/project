package Graphics;

import Logic.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by mostm on 08/07/2016.
 */

/**
 * this is the controller class for our main menu
 */
public class MainMenuController implements Initializable {

    Stage stage;
    @FXML
    private ImageView newGame;


    public void setStage(Stage stage){
        this.stage = stage;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newGame.setOnMouseClicked(event -> {
            FXMLLoader gameLoader = new FXMLLoader();
            gameLoader.setLocation(Main.class.getResource("/Graphics/game.fxml"));
            try {
                AnchorPane game = gameLoader.load();
                Scene gameScene = new Scene(game);
                GameController gameController = (GameController)gameLoader.getController();
                gameController.setStage(stage);
                gameScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
                stage.setScene(gameScene);
                stage.show();
                gameController.game = new Game();
                gameController.game.startGame();
                gameController.scheduleTimer();
                gameController.gridInit();
                gameController.setActions();
            }
            catch (IOException e){
                e.printStackTrace();
            }

        });
    }
}
