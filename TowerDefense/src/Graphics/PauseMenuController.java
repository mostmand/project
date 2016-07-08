package Graphics;

import Logic.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by mostm on 08/07/2016.
 */
public class PauseMenuController implements Initializable {
    private Stage stage;
    private Game game;

    @FXML
    private Button mainMenu;
    @FXML
    private Button resume;

    public void setGame(Game game){
        this.game = game;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainMenu.setOnAction(event -> {
            FXMLLoader mainMenuLoader = new FXMLLoader();
            mainMenuLoader.setLocation(Main.class.getResource("/Graphics/mainMenu.fxml"));
            try {
                AnchorPane mainMenuPane = mainMenuLoader.load();
                Scene mainMenuScene = new Scene(mainMenuPane);
                MainMenuController mainMenuController = (MainMenuController) mainMenuLoader.getController();
                mainMenuController.setStage(stage);
                mainMenuScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
                stage.setScene(mainMenuScene);
                stage.show();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
        resume.setOnAction(event -> {
            FXMLLoader gameLoader = new FXMLLoader();
            gameLoader.setLocation(Main.class.getResource("/Graphics/game.fxml"));
            try {
                AnchorPane gamePane = gameLoader.load();
                Scene gameScene = new Scene(gamePane);
                GameController gameController = (GameController)gameLoader.getController();
                gameController.setStage(stage);
                gameScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
                stage.setScene(gameScene);
                gameController.gridInit();
                game.startGame();
                stage.show();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
    }
}
